package om.subs.service.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.subs.service.SubscriptionService;
import org.common.common_utils.event.BookEvent;
import org.common.common_utils.event.enums.BookEventType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookEventKafkaConsumer {

    private final SubscriptionService subscriptionService;

    @KafkaListener(topics = "book-events", groupId = "book-events-group")
    public void consume(BookEvent event) {
        log.info("Получено событие: {}", event);

        switch (event.getEventType()) {
            case BookEventType.CREATE -> subscriptionService.processBookEventCreated(event);
            case BookEventType.DELETE -> subscriptionService.processBookEventDeleted(event);
            default -> log.warn("Неизвестный тип события: {}", event.getEventType());
        }
    }
}
