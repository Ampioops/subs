package om.subs.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
