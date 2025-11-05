package cotato.backend.domain.applicant.dto.response;

import cotato.backend.common.enums.Part;

public record ApplicationFormListResponse(
        Long id,
        String applicantName,
        Integer generation,
        Part part,
        Long likeCount
) {
}
