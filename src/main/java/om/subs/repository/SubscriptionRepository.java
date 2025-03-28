package om.subs.repository;

import om.subs.entity.Subscription;
import om.subs.model.enums.SubscriptionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>, JpaSpecificationExecutor<Subscription> {
    Page<Subscription> findByContactInfo_UserId (Integer userId, Pageable pageable);
    List<Subscription> findByTypeIn(List<SubscriptionType> types);

}
