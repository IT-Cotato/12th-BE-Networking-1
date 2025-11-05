package cotato.backend.dto.request;

import cotato.backend.domain.Applicant;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicantRequest {

    private String name;
    private int age;
    private String phoneNumber;

    public Applicant toEntity() {
        return Applicant.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .build();
    }
}
