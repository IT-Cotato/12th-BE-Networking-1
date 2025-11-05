package cotato.backend.api.dto.admin;

import jakarta.validation.constraints.*;

public record AdminRequest(
        @NotBlank @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자")
        String name,
        @NotNull @Min(20) @Max(80)
        Integer age,
        @NotBlank @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리")
        String phoneNumber,
        @NotBlank
        String role   // 예: "partjang" / "plan_lead" / "pr_lead" / "vice_president" / "president" / "education_lead"
) { }
