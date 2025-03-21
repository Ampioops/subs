package om.subs.service;

import jakarta.validation.constraints.NotNull;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import org.common.common_utils.event.BookEvent;
import org.springframework.data.domain.Page;

public interface SubscriptionService {

    @NotNull
    SubscriptionResponse createSubscription(@NotNull SubscriptionRequest request);

    @NotNull
    SubscriptionResponse updateSubscriptionInfo(@NotNull Integer id, @NotNull SubscriptionRequest request);

    @NotNull
    void deleteSubscriptionById(@NotNull Integer id);

    @NotNull
    Page<SubscriptionResponse> getSubscriptions(SubscriptionParam params, Integer offset, Integer limit);

    @NotNull
    SubscriptionResponse getSubscriptionById(@NotNull Integer id);

    @NotNull
    Page<SubscriptionResponse> getSubscriptionsByUser (@NotNull Integer userId, Integer offset, Integer limit);

    @NotNull
    void processBookEventDeleted (BookEvent bookEvent);

    @NotNull
    void processBookEventCreated (BookEvent bookEvent);
}
