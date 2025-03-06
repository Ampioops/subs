package om.subs.config.listener;

import om.subs.model.event.BookEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "book-events",
            groupId = "groupId",
            containerFactory = "bookKafkaListenerContainerFactory"
    )
    public void listener(BookEvent data){
        System.out.println("Listener!");
    }
}
