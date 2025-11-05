package cotato.backend.domain.applicant.entity;

import cotato.backend.common.entity.BaseEntity;
import cotato.backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "application_form_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_member_application_form",
                        columnNames = {"member_id", "application_form_id"}
                )
        }
)
public class ApplicationFormLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_form_id", nullable = false)
    private ApplicationForm applicationForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
