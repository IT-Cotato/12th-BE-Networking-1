package cotato.backend.domain.application;

import cotato.backend.domain.staff.StaffRoleConverter;
import cotato.backend.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "applications")
@Convert(converter = PartConverter.class) // 컨버터 적용
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1:N 비식별 관계 (Application이 User를 참조)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer period;

    // --- 스냅샷 데이터 ---
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer age; // TINYINT

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    // PartConverter가 자동 적용됨
    @Column(nullable = false, length = 20)
    private Part part;

    @Column(nullable = false)
    private Integer ability; // TINYINT

    @Column(nullable = false)
    private Integer passion; // TINYINT

    @Column(nullable = false)
    private LocalDateTime applicationTime;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public Application(User user, Integer period, String name, Integer age, String phoneNumber,
                       Part part, Integer ability, Integer passion, LocalDateTime applicationTime) {
        this.user = user;
        this.period = period;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.part = part;
        this.ability = ability;
        this.passion = passion;
        this.applicationTime = applicationTime;
    }
    public void incrementLikeCount() {
        this.likeCount += 1;
    }
}
