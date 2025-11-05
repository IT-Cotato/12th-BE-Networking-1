package cotato.backend.domain.applicant.dto.response;

import cotato.backend.common.enums.Part;
import cotato.backend.domain.applicant.entity.ApplicationForm;

import java.time.LocalDateTime;

public record ApplicationFormResponse(
        ApplicantResponse applicantResponse,
        Integer generation,
        Part part,
        Integer skillLevel,
        Integer passion,
        LocalDateTime submittedAt
) {
    public static ApplicationFormResponse from(ApplicationForm applicationForm) {
        return new ApplicationFormResponse(
                ApplicantResponse.from(applicationForm.getApplicant()),
                applicationForm.getGeneration(),
                applicationForm.getPart(),
                applicationForm.getSkillLevel(),
                applicationForm.getPassion(),
                applicationForm.getSubmittedAt()
        );
    }
}
