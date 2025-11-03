package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.Applicant;

public record ApplicantResponse(
        String name,
        Integer age,
        String phoneNum
) {
    public static ApplicantResponse from(Applicant applicant) {
        return new ApplicantResponse(applicant.getName(), applicant.getAge(), applicant.getPhoneNum());
    }
}
