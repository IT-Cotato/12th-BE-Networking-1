package cotato.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "club_admin",                                              // ← 안전한 테이블명으로 변경
        indexes = { @Index(name = "ix_admin_phone", columnList = "phoneNumber", unique = true) }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 11, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private AdminRole role;

    @CreationTimestamp
    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",
            nullable = false,
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
