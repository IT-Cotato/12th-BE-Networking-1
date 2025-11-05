package cotato.backend.domain.application.entity;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.like.entity.ApplicationLike;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "applications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @Column(name ="period", nullable = false)
    private Integer period;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", nullable = false, length = 20)
    private Part part;

    @Column(name = "ability", nullable = false)
    private Integer ability;

    @Column(name = "passion", nullable = false)
    private Integer passion;

    @Column(name = "application_time", nullable = false)
    private LocalDateTime applicationTime;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount = 0;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationLike> likesByApplication = new ArrayList<>();

    @Builder
    public Application(Applicant applicant, Integer period, Part part, Integer ability, Integer passion, LocalDateTime applicationTime) {
        this.applicant = applicant;
        this.period = period;
        this.part = part;
        this.ability = ability;
        this.passion = passion;
        this.applicationTime = applicationTime;
        this.likeCount = 0;
    }

    // 좋아요 수 증가
    public void incrementLikeCount() {
        this.likeCount++;
    }

    // 좋아요 수 감소
    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
}
