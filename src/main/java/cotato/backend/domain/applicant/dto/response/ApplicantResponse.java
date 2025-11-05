package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.ApplicantEntity;

public record ApplicantResponse(
	Long id,
	String name,
    int age,
    String phoneNumber
) {
    public static ApplicantResponse from(ApplicantEntity applicant) {
        return new ApplicantResponse(
                applicant.getId(),
                applicant.getName(),
                applicant.getAge(),
                applicant.getPhoneNumber()
        );
    }
}
