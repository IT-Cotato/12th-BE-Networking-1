package cotato.backend.applicant.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantCreateResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private Integer birthYear;
}
