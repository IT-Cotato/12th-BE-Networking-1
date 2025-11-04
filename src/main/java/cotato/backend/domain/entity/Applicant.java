package cotato.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "applicant", indexes = {
        @Index(name = "ix_applicant_phone", columnList = "phoneNumber", unique = true)
})
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Applicant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 11, unique = true)
    private String phoneNumber;

    // 테이블 생성 시 제로 날짜 방지 (DB 기본값을 현재시간으로)
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
