package om.user_notifications.service.impl;

import lombok.RequiredArgsConstructor;
import om.user_notifications.mapper.SubscriptionMapper;
import om.user_notifications.model.request.SubscriptionRequest;
import om.user_notifications.model.response.SubscriptionResponse;
import om.user_notifications.repository.SubscriptionRepository;
import om.user_notifications.service.SubscriptionService;
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
    public Page<SubscriptionResponse> getSubscriptions(SubscriptionRequest params, Integer offset, Integer limit) {
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
