package om.subs.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.subs.client.LibraryClient;
import om.subs.entity.ContactInfo;
import om.subs.entity.Subscription;
import om.subs.kafka.producer.NotificationEventKafkaProducer;
import om.subs.mapper.SubscriptionMapper;
import om.subs.model.enums.SubscriptionType;
import om.subs.service.SubscriptionService;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import om.subs.repository.ContactInfoRepository;
import om.subs.repository.SubscriptionRepository;
import om.subs.specification.SubscriptionSpecification;
import org.common.common_utils.event.BookEvent;
import org.common.common_utils.event.NotificationEvent;
import org.common.common_utils.response.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
    private final NotificationEventKafkaProducer notificationEventKafkaProducer;
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

        libraryClient.getBookById(bookId)
                .flatMapMany(book -> Flux.fromIterable(createSubscriptions)
                        .filter(subscription -> isSubscriptionRelevant(subscription, bookEvent))
                        .map(subscription -> generateNotificationEvent(subscription, book)) // Формируем NotificationEvent
                )
                .doOnNext(notificationEventKafkaProducer::send) // Отправляем уже сформированное NotificationEvent
                .subscribe();
    }

    private boolean isSubscriptionRelevant(Subscription subscription, BookEvent bookEvent) {
        return switch (subscription.getType()) {
            case CREATE_BOOK_BY_SPECIFIC_AUTHOR -> subscription.getReferenceId().equals(bookEvent.getAuthorId());
            case CREATE_BOOK_BY_SPECIFIC_GENRE -> subscription.getReferenceId().equals(bookEvent.getGenreId());
            default -> false;
        };
    }

    private NotificationEvent generateNotificationEvent(Subscription subscription, BookResponseDTO book) {
        String message;

        switch (subscription.getType()) {
            case CREATE_BOOK_BY_SPECIFIC_AUTHOR:
                String authorFirstName = book.getAuthor() != null ? book.getAuthor().getFirstName() : "";
                String authorLastName = book.getAuthor() != null ? book.getAuthor().getLastName() : "";
                String authorFullName = (authorFirstName + " " + authorLastName).trim().replaceAll("\\s+", " ");

                message = String.format("Ваш любимый автор %s опубликовал книгу: \"%s\"",
                        authorFullName.isEmpty() ? "Неизвестный автор" : authorFullName,
                        book.getTitle());
                break;

            case CREATE_BOOK_BY_SPECIFIC_GENRE:
                String genreName = book.getGenre() != null ? book.getGenre().getName() : "Неизвестный жанр";
                message = String.format("В вашем любимом жанре %s опубликована книга: \"%s\"",
                        genreName, book.getTitle());
                break;

            case DELETE_BOOK:
                message = String.format("Ваша книга:: \"%s\" была удалена",
                        book.getTitle());
                break;

            default:
                throw new IllegalArgumentException("Неизвестный тип подписки: " + subscription.getType());
        }

        return NotificationEvent.builder()
                .mail(subscription.getContactInfo().getEmail())
                .message(message)
                .build();
    }
}