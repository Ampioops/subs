package om.user_notifications.service;

import jakarta.validation.constraints.NotNull;
import om.user_notifications.model.param.ContactInfoParam;
import om.user_notifications.model.request.ContactInfoRequest;
import om.user_notifications.model.request.SubscriptionRequest;
import om.user_notifications.model.response.ContactInfoResponse;
import om.user_notifications.model.response.SubscriptionResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContactInfoService {
    @NotNull
    ContactInfoResponse createContactInfo(@NotNull ContactInfoRequest request);

    @NotNull
    ContactInfoResponse updateContactInfo(@NotNull UUID id, @NotNull ContactInfoRequest request);

    @NotNull
    void deleteContactInfoById(@NotNull UUID id);

    @NotNull
    Page<ContactInfoResponse> getUsersContactInfo(ContactInfoParam params, Integer offset, Integer limit);

    @NotNull
    ContactInfoResponse getContactInfoByUserId(@NotNull UUID userId);
}
