package om.user_notifications.repository;

import om.user_notifications.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, UUID> {
}
