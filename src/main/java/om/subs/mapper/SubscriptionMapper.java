package om.subs.mapper;

import om.subs.entity.Subscription;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "contactInfo.subscription", ignore = true)
    SubscriptionResponse toResponse(Subscription subscription);
    Subscription toSubscription(SubscriptionRequest subscriptionRequest);

}
