package om.subs.model.response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import om.subs.entity.ContactInfo;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Builder
public class SubscriptionResponse {
    private Integer id;
    private ContactInfoResponse contactInfo;
    private String type;
    private Integer referenceId;
    private LocalDateTime createdAt;
}
