package cotato.backend.domain.applicant.entity;

import cotato.backend.common.enums.Part;
import cotato.backend.domain.applicant.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "application_form")
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "generation", nullable = false)
    private Integer generation;

    @Column(name = "part", nullable = false)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(name = "skill_level", nullable = false)
    @Builder.Default
    private Integer skillLevel = 5;

    @Column(name = "passion", nullable = false)
    @Builder.Default
    private Integer passion = 5;

    @CreatedDate
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
