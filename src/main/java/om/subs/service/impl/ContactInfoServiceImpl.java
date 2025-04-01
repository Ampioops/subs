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
import om.subs.specification.ContactInfoSpecification;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;
    private final ContactInfoSpecification contactInfoSpecification;

    @NotNull
    @Transactional
    public ContactInfoResponse createContactInfo(@NotNull ContactInfoRequest request) {
        if (request == null || request.getEmail() == null || request.getNumberPhone() == null) {
            throw new RuntimeException("Invalid contact information");
        }

        ContactInfo contactInfo =  ContactInfo.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .numberPhone(request.getNumberPhone())
                .build();

        return contactInfoMapper.toResponse(contactInfoRepository.save(contactInfo));
    }

    @NotNull
    @Transactional
    public ContactInfoResponse updateUserContactInfo(Integer id, ContactInfoRequest request) {
        ContactInfo contactInfo = contactInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id = [%s] not found".formatted(id)));
        contactInfo.setUserId(request.getUserId());
        contactInfo.setEmail(request.getEmail());
        contactInfo.setNumberPhone(request.getNumberPhone());
        return contactInfoMapper.toResponse(contactInfoRepository.save(contactInfo));
    }

    @NotNull
    public void deleteContactInfoById(Integer id) {
        ContactInfo contactInfo = contactInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id = [%s] not found".formatted(id)));
        contactInfoRepository.delete(contactInfo);
    }

    @NotNull
    public Page<ContactInfoResponse> getUsersContactInfo(ContactInfoParam params, Integer offset, Integer limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return contactInfoRepository.findAll(contactInfoSpecification.build(params), pageRequest)
                .map(contactInfoMapper::toResponse);
    }

    @NotNull
    public ContactInfoResponse getContactInfoByUserId(Integer userId) {
        ContactInfo contactInfo = contactInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Book with id = [%s] not found".formatted(userId)));;
        return contactInfoMapper.toResponse(contactInfo);
    }
}
