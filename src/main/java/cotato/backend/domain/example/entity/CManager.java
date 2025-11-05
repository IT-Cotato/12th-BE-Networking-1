package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class CManager {
    public enum Role {
        PRESIDENT("회장"),
        VICE_PRESIDENT("부회장"),
        PARTJANG("파트장"),
        PLANNER_LEADER("기획팀장"),
        PROMOTION_LEADER("홍보팀장"),
        EDUCATION_LEADER("교육팀장");

        private final String koreanName;

        Role(String koreanName) {
            this.koreanName = koreanName;
        }

        public String getKoreanName() {
            return koreanName;
        }

        public static Role fromKoreanName(String koreanName) {
            for (Role role : Role.values()) {
                if (role.getKoreanName().equals(koreanName)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("유효하지 않은 운영진 역할입니다.");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    private String name;
    private Integer age;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 생성/수정 시간
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}