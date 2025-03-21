package om.subs.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.common.common_utils.event.NotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventKafkaProducer {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void send(NotificationEvent notificationEvent) {
        kafkaTemplate.send("notification-events", notificationEvent);
    }
}
