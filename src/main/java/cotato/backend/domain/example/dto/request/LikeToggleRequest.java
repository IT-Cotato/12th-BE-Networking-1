package cotato.backend.domain.example.dto.request;

import lombok.Data;

@Data
public class LikeToggleRequest {
    private Long adminId;
    private Long applicationId;
}


