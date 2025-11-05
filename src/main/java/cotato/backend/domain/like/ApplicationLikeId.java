package cotato.backend.domain.like;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

// 복합키를 위한 ID 클래스
@NoArgsConstructor
@Embeddable
public class ApplicationLikeId implements java.io.Serializable {
    private Long application;
    private Long staff;
}