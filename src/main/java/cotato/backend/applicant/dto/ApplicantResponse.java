package cotato.backend.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;

    public static ApplicantResponse from(Applicant applicant) {
        return new ApplicantResponse(
                applicant.getId(),
                applicant.getName(),
                applicant.getAge(),
                applicant.getPhoneNumber()
        );
    }
}
