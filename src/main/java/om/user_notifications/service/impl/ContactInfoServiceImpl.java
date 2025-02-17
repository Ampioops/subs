package om.user_notifications.service.impl;

import lombok.RequiredArgsConstructor;
import om.user_notifications.mapper.ContactInfoMapper;
import om.user_notifications.model.param.ContactInfoParam;
import om.user_notifications.model.request.ContactInfoRequest;
import om.user_notifications.model.response.ContactInfoResponse;
import om.user_notifications.repository.ContactInfoRepository;
import om.user_notifications.service.ContactInfoService;
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
    public ContactInfoResponse updateContactInfo(UUID id, ContactInfoRequest request) {
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
