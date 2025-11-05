package cotato.backend.api.dto.application;

import jakarta.validation.constraints.NotNull;

public record LikeRequest(
        @NotNull Long adminId
) { }
