package om.subs.service.impl;

import lombok.RequiredArgsConstructor;
import om.subs.mapper.ContactInfoMapper;
import om.subs.model.param.ContactInfoParam;
import om.subs.model.request.ContactInfoRequest;
import om.subs.model.response.ContactInfoResponse;
import om.subs.repository.ContactInfoRepository;
import om.subs.service.ContactInfoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;

    @Override
    public ContactInfoResponse createContactInfo(ContactInfoRequest request) {
        return null;
    }

    @Override
    public ContactInfoResponse updateUserContactInfo(UUID id, ContactInfoRequest request) {
        return null;
    }

    @Override
    public void deleteContactInfoById(UUID id) {

    }

    @Override
    public Page<ContactInfoResponse> getUsersContactInfo(ContactInfoParam params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public ContactInfoResponse getContactInfoByUserId(UUID userId) {
        return null;
    }
}
