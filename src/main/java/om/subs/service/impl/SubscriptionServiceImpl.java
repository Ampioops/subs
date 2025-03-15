package om.subs.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.subs.client.LibraryClient;
import om.subs.entity.ContactInfo;
import om.subs.entity.Subscription;
import om.subs.mapper.SubscriptionMapper;
import om.subs.model.enums.SubscriptionType;
import om.subs.model.event.BookEvent;
import om.subs.model.event.SubEvent;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import om.subs.repository.ContactInfoRepository;
import om.subs.repository.SubscriptionRepository;
import om.subs.service.SubscriptionService;
import om.subs.specification.SubscriptionSpecification;
import org.common.common_utils.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final ContactInfoRepository contactInfoRepository;
    private final SubscriptionSpecification subscriptionSpecification;
    private final KafkaTemplate<String, SubEvent> kafkaTemplate;
    private final LibraryClient libraryClient;

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {

        ContactInfo contactInfo = contactInfoRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("No contact info for this user"));
        Subscription subscription = subscriptionMapper.toSubscription(request);

        subscription.setContactInfo(contactInfo);

        return subscriptionMapper.toResponse(subscriptionRepository.save(subscription));
    }

    @Override
    public SubscriptionResponse updateSubscriptionInfo(Integer id, SubscriptionRequest request) {
        return null;
    }

    @Override
    public void deleteSubscriptionById(Integer id) {

    }

    @Override
    public Page<SubscriptionResponse> getSubscriptions(SubscriptionParam params, Integer offset, Integer limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return subscriptionRepository.findAll(subscriptionSpecification.build(params), pageRequest)
                .map(subscriptionMapper::toResponse);
    }

    @Override
    public SubscriptionResponse getSubscriptionById(Integer id) {
        return null;
    }

    @Override
    public Page<SubscriptionResponse> getSubscriptionsByUser(Integer userId, Integer offset, Integer limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return subscriptionRepository.findByContactInfo_UserId(userId, pageRequest)
                .map(subscriptionMapper::toResponse);
    }

    @Override
    public void processBookEventDeleted(BookEvent bookEvent) {
        List<SubscriptionType> deleteTypes = List.of(
                SubscriptionType.DELETE_BOOK
        );
        List<Subscription> deleteSubscriptions = subscriptionRepository.findByTypeIn(deleteTypes);
    }

    @Override
    public void processBookEventCreated(BookEvent bookEvent) {

        List<SubscriptionType> createTypes = new ArrayList<>();

        if (bookEvent.getAuthorId() != null) {
            createTypes.add(SubscriptionType.CREATE_BOOK_BY_SPECIFIC_AUTHOR);
        }
        if (bookEvent.getGenreId() != null) {
            createTypes.add(SubscriptionType.CREATE_BOOK_BY_SPECIFIC_GENRE);
        }

        List<Subscription> createSubscriptions = subscriptionRepository.findByTypeIn(createTypes);

        Integer bookId = bookEvent.getBookId();

        for (Subscription subscription : createSubscriptions) {
            if (subscription.getType().equals(SubscriptionType.CREATE_BOOK_BY_SPECIFIC_AUTHOR)
                    && !subscription.getReferenceId().equals(bookEvent.getAuthorId())) {
                continue;
            }

            if (subscription.getType().equals(SubscriptionType.CREATE_BOOK_BY_SPECIFIC_GENRE)
                    && !subscription.getReferenceId().equals(bookEvent.getGenreId())) {
                continue;
            }

            Mono<BookResponseDTO> bookMono = libraryClient.getBookById(bookId);

            bookMono.flatMap(book -> {
                SubEvent event = new SubEvent();
                event.setUserId(subscription.getContactInfo().getUserId());
                event.setBookTitle(book.getTitle());
                event.setGenre(book.getGenre() != null ? book.getGenre().getName() : "Неизвестный жанр");
                event.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);

                return Mono.fromRunnable(() -> kafkaTemplate.send("sub-events", event));
            }).subscribe(); // Запускаем асинхронную обработку
        }
    }
    // Я должен найти все подписки пользователей на этот жанр
    // Отправить в кафку событие с данными о: Айди пользователя, название новой книги, жанр, автор
    // Айди взять из БД, название книги получить через запрос в монолит

}
