package om.subs.service.impl;

import lombok.RequiredArgsConstructor;
import om.subs.mapper.SubscriptionMapper;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import om.subs.repository.SubscriptionRepository;
import om.subs.service.SubscriptionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {



        return null;
    }

    @Override
    public SubscriptionResponse updateSubscriptionInfo(UUID id, SubscriptionRequest request) {
        return null;
    }

    @Override
    public void deleteSubscriptionById(UUID id) {

    }

    @Override
    public Page<SubscriptionResponse> getSubscriptions(SubscriptionParam params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public SubscriptionResponse getSubscriptionById(UUID id) {
        return null;
    }

    @Override
    public Page<SubscriptionResponse> getSubscriptionsByUser(UUID userId) {
        return null;
    }
}
