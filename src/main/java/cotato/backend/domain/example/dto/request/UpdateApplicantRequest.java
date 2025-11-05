package cotato.backend.domain.example.dto.request;

import lombok.Data;

@Data
public class UpdateApplicantRequest {
    private String name;
    private Integer age;
    private String phoneNumber;
}


