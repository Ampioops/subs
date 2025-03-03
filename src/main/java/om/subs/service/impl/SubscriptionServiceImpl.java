package om.subs.service.impl;

import lombok.RequiredArgsConstructor;
import om.subs.entity.ContactInfo;
import om.subs.entity.Subscription;
import om.subs.mapper.SubscriptionMapper;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import om.subs.repository.ContactInfoRepository;
import om.subs.repository.SubscriptionRepository;
import om.subs.service.SubscriptionService;
import om.subs.specification.SubscriptionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final ContactInfoRepository contactInfoRepository;
    private final SubscriptionSpecification subscriptionSpecification;

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {

        ContactInfo contactInfo = contactInfoRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("No contact info for this user"));
        Subscription subscription = subscriptionMapper.toSubscription(request);

        subscription.setContactInfo(contactInfo);

        return subscriptionMapper.toResponse(subscriptionRepository.save(subscription));
    }

    @Override
    public SubscriptionResponse updateSubscriptionInfo(Integer id, SubscriptionRequest request) {
        return null;
    }

    @Override
    public void deleteSubscriptionById(Integer id) {

    }

    @Override
    public Page<SubscriptionResponse> getSubscriptions(SubscriptionParam params, Integer offset, Integer limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return subscriptionRepository.findAll(subscriptionSpecification.build(params), pageRequest)
                .map(subscriptionMapper::toResponse);
    }

    @Override
    public SubscriptionResponse getSubscriptionById(Integer id) {
        return null;
    }

    @Override
    public Page<SubscriptionResponse> getSubscriptionsByUser(Integer userId, Integer offset, Integer limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return subscriptionRepository.findByContactInfo_UserId(userId, pageRequest)
                .map(subscriptionMapper::toResponse);
    }
}
