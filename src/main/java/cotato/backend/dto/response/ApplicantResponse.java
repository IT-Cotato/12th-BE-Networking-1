package cotato.backend.dto.response;

import cotato.backend.domain.Applicant;
import lombok.Getter;

@Getter
public class ApplicantResponse {
    private final Long applicantId;
    private final String name;
    private final int age;
    private final String phoneNumber;

    public ApplicantResponse(Applicant applicant) {
        this.applicantId = applicant.getApplicantId();
        this.name = applicant.getName();
        this.age = applicant.getAge();
        this.phoneNumber = applicant.getPhoneNumber();
    }
}