package cotato.backend.applicant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicantUpdateRequestDto {

    private String name;
    private int birthYear;
}
