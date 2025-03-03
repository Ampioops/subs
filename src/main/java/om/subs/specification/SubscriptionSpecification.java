package om.subs.specification;

import om.subs.entity.Subscription;
import om.subs.model.param.SubscriptionParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubscriptionSpecification {
    public Specification<Subscription> build(SubscriptionParam params) {
        if (params == null) {
            return null;
        }
        return withUserId(params.getUserIdCont())
                .and(withReferenceId(params.getReferenceIdCont()))
                .and(withCreatedAfter(params.getCreatedAfter()))
                .and(withCreatedBefore(params.getCreatedBefore()));
    }

    private static Specification<Subscription> withUserId(Integer userId) {
        return ((root, query, criteriaBuilder) ->
                userId != null ? criteriaBuilder.equal(root.get("userId"), userId) : criteriaBuilder.conjunction());
    }

    private static Specification<Subscription> withReferenceId(Integer referenceId) {
        return ((root, query, criteriaBuilder) ->
                referenceId != null ? criteriaBuilder.equal(root.get("referenceId"), referenceId) : criteriaBuilder.conjunction());
    }

    private static Specification<Subscription> withCreatedAfter(LocalDateTime createdAfter) {
        return ((root, query, criteriaBuilder) ->
                createdAfter != null ? criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdAfter) : criteriaBuilder.conjunction());
    }

    private static Specification<Subscription> withCreatedBefore(LocalDateTime createdBefore) {
        return ((root, query, criteriaBuilder) ->
                createdBefore != null ? criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdBefore) : criteriaBuilder.conjunction());
    }
}
