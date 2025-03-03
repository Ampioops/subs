package om.subs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@Table(name = "contact_info")
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "contactInfo")
    private Subscription subscription;
}
