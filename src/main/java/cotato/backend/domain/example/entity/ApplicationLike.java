package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "application_like",
        uniqueConstraints = @UniqueConstraint(columnNames = {"application_id", "staff_id"}))
@Getter @NoArgsConstructor
public class ApplicationLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Application application;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Staff staff;

    @CreationTimestamp private LocalDateTime createdAt;

    @Builder
    public ApplicationLike(Application application, Staff staff) {
        this.application = application; this.staff = staff;
    }
}
