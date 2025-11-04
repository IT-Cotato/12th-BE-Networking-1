package cotato.backend.appllication.entity;

import cotato.backend.applicant.entity.Applicant;
import cotato.backend.global.Part;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int birthYear;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(name = "generation", nullable = false)
    private Integer generation;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", nullable = false)
    private Part part;

    @Column(name = "ability", nullable = false)
    private Integer ability;

    @Column(name = "passion", nullable = false)
    private Integer passion;

    @Column(name = "application_time", nullable = false)
    private LocalDateTime applicationTime;

    @Column(nullable = false)
    private long likeCount;

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        if (this.likeCount > 0) this.likeCount--;
    }
}