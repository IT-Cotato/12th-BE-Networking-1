package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "application", indexes = @Index(name = "idx_application_period", columnList = "period"))
@Getter @NoArgsConstructor
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Applicant applicant;

    @Column(nullable = false)
    private Integer period;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Part part;

    @Column(nullable = false) private Integer ability;
    @Column(nullable = false) private Integer passion;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime applicationTime;

    @CreationTimestamp private LocalDateTime createdAt;

    @Builder
    public Application(Applicant applicant, Integer period, Part part,
                       Integer ability, Integer passion, String phoneNumber,
                       LocalDateTime applicationTime) {
        this.applicant = applicant; this.period = period; this.part = part;
        this.ability = ability; this.passion = passion;
        this.phoneNumber = phoneNumber; this.applicationTime = applicationTime;
    }
}
