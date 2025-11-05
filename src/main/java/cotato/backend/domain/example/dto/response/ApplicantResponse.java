package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.Applicant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicantResponse {
    // id, 이름, 나이, 연락처
    private Long applicantId;
    private String name;
    private Integer age;
    private String phoneNumber;

    public static ApplicantResponse of(Applicant applicant) {
        return ApplicantResponse.builder()
                .applicantId(applicant.getApplicantId())
                .name(applicant.getName())
                .age(applicant.getAge())
                .phoneNumber(applicant.getPhoneNumber())
                .build();
    }
}
