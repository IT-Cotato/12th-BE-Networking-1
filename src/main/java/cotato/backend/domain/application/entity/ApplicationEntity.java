package cotato.backend.domain.application.entity;

import cotato.backend.domain.applicant.entity.ApplicantEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "application")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private Long id;

	@Column(nullable = false)
	private int period;

    @Column(nullable = false)
    private int grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Part part;

    @Column(nullable = false)
    private int ability;

    @Column(nullable = false)
    private int passion;

    @Column(name = "application_time", nullable = false)
    private LocalDateTime applicationTime;

    // 지원자와 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private ApplicantEntity applicant;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Builder
    public ApplicationEntity(ApplicantEntity applicant, int period, int grade,
                              Part part, int ability, int passion,
                              LocalDateTime applicationTime, int likeCount) {
        this.applicant = applicant;
        this.period = period;
        this.grade = grade;
        this.part = part;
        this.ability = ability;
        this.passion = passion;
        this.applicationTime = applicationTime;
        this.likeCount = 0;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

}