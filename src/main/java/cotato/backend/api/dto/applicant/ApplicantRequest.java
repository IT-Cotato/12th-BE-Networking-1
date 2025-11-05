package cotato.backend.api.dto.applicant;

import jakarta.validation.constraints.*;

public record ApplicantRequest(
        @NotBlank @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자")
        String name,
        @NotNull @Min(1) @Max(150)
        Integer age,
        @NotBlank @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리")
        String phoneNumber
) { }
