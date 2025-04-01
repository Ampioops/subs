package om.subs.specification;

import om.subs.entity.ContactInfo;
import om.subs.model.param.ContactInfoParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ContactInfoSpecification {
    public Specification<ContactInfo> build(ContactInfoParam params) {
        if (params == null) {
            return null;
        }
        return withUserId(params.getUserIdCont())
                .and(withEmailCont(params.getEmailCont()))
                .and(withNumberPhoneCont(params.getNumberPhoneCont()));
    }

    private static Specification<ContactInfo> withUserId(Integer userId) {
        return ((root, query, criteriaBuilder) ->
                userId != null ? criteriaBuilder.equal(root.get("userId"), userId) : criteriaBuilder.conjunction());
    }

    private static Specification<ContactInfo> withEmailCont(String email) {
        return ((root, query, criteriaBuilder) ->
                email != null ? criteriaBuilder.like(root.get("email"), email) : criteriaBuilder.conjunction());
    }

    private static Specification<ContactInfo> withNumberPhoneCont(String phoneNumber) {
        return ((root, query, criteriaBuilder) ->
                phoneNumber != null ? criteriaBuilder.like(root.get("numberPhone"), phoneNumber) : criteriaBuilder.conjunction());
    }

}
