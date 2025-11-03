package cotato.backend.appllication.entity;

import cotato.backend.applicant.entity.Applicant;
import cotato.backend.global.enums.Part;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_number", nullable = false)
    private Applicant applicant;

    @Column(name = "generation", nullable = false)
    private Integer generation;

    @Enumerated(EnumType.STRING)
    @Column(name = "part", nullable = false)
    private Part part;

    @Column(name = "skill", nullable = false)
    private Integer skill;

    @Column(name = "passion", nullable = false)
    private Integer passion;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}