package cotato.backend.domain.applicant.dto.request;

import cotato.backend.domain.applicant.enums.Status;
import jakarta.validation.constraints.NotNull;

public record ApplicationFormStatusUpdateRequest(
        @NotNull(message = "상태는 필수입니다.")
        Status status
) {
}
