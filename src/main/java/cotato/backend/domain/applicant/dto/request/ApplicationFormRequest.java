package cotato.backend.domain.applicant.dto.request;

import cotato.backend.common.enums.Part;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ApplicationFormRequest(
        @NotNull(message = "지원자 정보는 필수입니다.")
        @Valid
        ApplicantRequest applicant,

        @NotNull(message = "기수는 필수입니다.")
        @Min(value = 1, message = "기수는 1기 이상이어야 합니다.")
        Integer generation,

        @NotNull(message = "파트는 필수입니다.")
        Part part,

        @Min(value = 0, message = "실력 레벨은 0 이상이어야 합니다.")
        @Max(value = 10, message = "실력 레벨은 10 이하여야 합니다.")
        Integer skillLevel,

        @Min(value = 0, message = "열정 레벨은 0 이상이어야 합니다.")
        @Max(value = 10, message = "열정 레벨은 10 이하여야 합니다.")
        Integer passion
) {
}
