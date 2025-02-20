package om.subs.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import om.subs.entity.ContactInfo;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Builder
public class SubscriptionRequest {
    private UUID userId;
    private String type;
    private UUID referenceId;
    private LocalDateTime createdAt;
}
