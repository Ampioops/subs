package om.subs.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class ContactInfoResponse {
    private Integer userId;
    private String numberPhone;
    private String email;
    private SubscriptionResponse subscription;
}
