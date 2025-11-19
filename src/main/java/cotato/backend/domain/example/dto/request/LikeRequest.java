package cotato.backend.domain.example.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeRequest {

    private int applicantId;
    private String adminId;
}
