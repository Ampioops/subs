package om.subs.repository;

import om.subs.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer>, JpaSpecificationExecutor<ContactInfo> {
}
