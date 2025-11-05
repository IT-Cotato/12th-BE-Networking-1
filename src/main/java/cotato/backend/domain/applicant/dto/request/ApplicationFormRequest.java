package cotato.backend.domain.applicant.dto.request;

import cotato.backend.common.enums.Part;

public record ApplicationFormRequest(
        ApplicantRequest applicant,
        Integer generation,
        Part part,
        Integer skillLevel,
        Integer passion
) {
}
