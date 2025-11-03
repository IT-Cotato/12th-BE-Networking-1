package cotato.backend.domain.like.dto.request;

import jakarta.validation.constraints.NotNull;

public record LikeRequest(
        @NotNull(message = "staffID는 필수입니다")
        Long staffId
) {

}
