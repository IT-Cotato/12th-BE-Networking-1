package cotato.backend.domain.applicant.entity;


import cotato.backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "applicant")
public class Applicant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "phone_num", nullable = false, unique = true, length = 11)
    private String phoneNum;

    @Column(name = "inactive", nullable = false)
    @Builder.Default
    private boolean inactive = false;

    @Column(name = "inactive_at")
    private LocalDateTime inactiveAt;
}
