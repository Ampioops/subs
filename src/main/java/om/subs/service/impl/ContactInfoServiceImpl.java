package om.subs.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import om.subs.entity.ContactInfo;
import om.subs.mapper.ContactInfoMapper;
import om.subs.model.param.ContactInfoParam;
import om.subs.model.request.ContactInfoRequest;
import om.subs.model.response.ContactInfoResponse;
import om.subs.repository.ContactInfoRepository;
import om.subs.service.ContactInfoService;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;

    @NotNull
    @Transactional
    public ContactInfoResponse createContactInfo(@NotNull ContactInfoRequest request) {
        if (request == null || request.getEmail() == null || request.getNumberPhone() == null) {
            throw new RuntimeException("Invalid contact information");
        }

        ContactInfo contactInfo =  contactInfoMapper.toContactInfo(request);

        return contactInfoMapper.toResponse(contactInfoRepository.save(contactInfo));
    }

    @NotNull
    @Transactional
    public ContactInfoResponse updateUserContactInfo(UUID id, ContactInfoRequest request) {
        return null;
    }

    @NotNull
    public void deleteContactInfoById(UUID id) {

    }

    @NotNull
    public Page<ContactInfoResponse> getUsersContactInfo(ContactInfoParam params, Integer offset, Integer limit) {
        return null;
    }

    @NotNull
    public ContactInfoResponse getContactInfoByUserId(UUID userId) {
        return null;
    }
}
