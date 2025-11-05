package cotato.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "application", indexes = {
        @Index(name = "ix_app_period_likes", columnList = "period, likeCount DESC"),
        @Index(name = "ix_app_period_time", columnList = "period, applicationTime DESC")
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer period;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Part part;

    @Column(nullable = false)
    private Integer ability;

    @Column(nullable = false)
    private Integer passion;

    @Column(nullable = false, length = 11)
    private String phoneNumberCache;

    @Column(nullable = false)
    private LocalDateTime applicationTime;

    @Column(nullable = false)
    private Integer likeCount;

    @CreationTimestamp
    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
