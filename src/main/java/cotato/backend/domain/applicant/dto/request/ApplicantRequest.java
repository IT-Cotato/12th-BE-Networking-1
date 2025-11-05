package cotato.backend.domain.applicant.dto.request;

import lombok.Data;

@Data
public class ApplicantRequest {
    private String name;
    private Integer age;
    private String phoneNum;
}
