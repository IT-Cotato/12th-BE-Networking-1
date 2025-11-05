package cotato.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "application_like",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_app_like_admin",
                columnNames = {"application_id", "admin_id"}     // FK 컬럼명 그대로 유지
        )
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")                      // club_admin(id)로 매핑됨
    private Admin admin;

    @CreationTimestamp
    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
