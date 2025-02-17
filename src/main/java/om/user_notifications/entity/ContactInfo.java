package om.user_notifications.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "contact_info")
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    @Id
    private UUID userId;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "email")
    private String email;
}
