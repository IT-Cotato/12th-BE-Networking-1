package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Application {
    public enum Part {
        PLANNER("기획"),
        DESIGNER("디자이너"),
        FRONTEND("프론트엔드"),
        BACKEND("백엔드");

        private final String koreanName;

        Part(String koreanName) {
            this.koreanName = koreanName;
        }

        public String getKoreanName() {
            return koreanName;
        }

        public static Part fromKoreanName(String koreanName) {
            for (Part part : Part.values()) {
                if (part.getKoreanName().equals(koreanName)) {
                    return part;
                }
            }
            throw new IllegalArgumentException("유효하지 않은 파트명입니다.");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable=false)
    private Applicant applicant;

    private Integer period;
    @Enumerated(EnumType.STRING)
    private Part part;
    private Integer ability;
    private Integer passion;

    @Column(nullable=false, updatable=false)
    private LocalDateTime applicationTime;

    // 좋아요 수는 기본이 0
    private Integer likesCount=0;
}
