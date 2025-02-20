package om.subs.model.response;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import om.subs.entity.Subscription;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Builder
public class ContactInfoResponse {
    private UUID userId;
    private String numberPhone;
    private String email;
    private Subscription subscription;
}
