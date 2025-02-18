package om.subs.service;

import jakarta.validation.constraints.NotNull;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SubscriptionService {

    @NotNull
    SubscriptionResponse createSubscription(@NotNull SubscriptionRequest request);

    @NotNull
    SubscriptionResponse updateSubscriptionInfo(@NotNull UUID id, @NotNull SubscriptionRequest request);

    @NotNull
    void deleteSubscriptionById(@NotNull UUID id);

    @NotNull
    Page<SubscriptionResponse> getSubscriptions(SubscriptionParam params, Integer offset, Integer limit);

    @NotNull
    SubscriptionResponse getSubscriptionById(@NotNull UUID id);

    @NotNull
    Page<SubscriptionResponse> getSubscriptionsByUser (@NotNull UUID userId);

}
