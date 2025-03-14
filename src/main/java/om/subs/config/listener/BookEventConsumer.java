package om.subs.config.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.subs.model.event.BookEvent;
import om.subs.service.SubscriptionService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookEventConsumer {

    private final SubscriptionService subscriptionService;

    @KafkaListener(topics = "book-events", groupId = "book-events-group")
    public void consume(BookEvent event) {
        log.info("Получено событие: {}", event);

        switch (event.getEventType()) {
            case "CREATED" -> subscriptionService.processBookEventCreated(event);
            case "DELETED" -> subscriptionService.processBookEventDeleted(event);
            default -> log.warn("Неизвестный тип события: {}", event.getEventType());
        }
    }
}
