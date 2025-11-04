package cotato.backend.domain.example.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class LikeId implements Serializable {
    private Long applicationId;
    private Long managerId;
}
