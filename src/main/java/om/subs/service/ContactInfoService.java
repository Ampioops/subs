package om.subs.service;

import jakarta.validation.constraints.NotNull;
import om.subs.model.param.ContactInfoParam;
import om.subs.model.request.ContactInfoRequest;
import om.subs.model.response.ContactInfoResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContactInfoService {
    @NotNull
    ContactInfoResponse createContactInfo(@NotNull ContactInfoRequest request);

    @NotNull
    ContactInfoResponse updateUserContactInfo(@NotNull UUID userId, @NotNull ContactInfoRequest request);

    @NotNull
    void deleteContactInfoById(@NotNull UUID id);

    @NotNull
    Page<ContactInfoResponse> getUsersContactInfo(ContactInfoParam params, Integer offset, Integer limit);

    @NotNull
    ContactInfoResponse getContactInfoByUserId(@NotNull UUID userId);
}
