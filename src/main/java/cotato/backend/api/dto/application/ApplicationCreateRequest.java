package cotato.backend.api.dto.application;

import jakarta.validation.constraints.*;

public record ApplicationCreateRequest(
        @NotBlank @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자")
        String name,
        @NotNull @Min(1)
        Integer period,
        @NotNull @Min(22) @Max(30)
        Integer age,
        @NotBlank @Pattern(regexp = "^(기획|디자이너|프론트엔드|백엔드)$",
                message = "파트는 기획/디자이너/프론트엔드/백엔드 중 하나")
        String part,
        @NotNull @Min(0) @Max(10)
        Integer ability,
        @NotNull @Min(0) @Max(10)
        Integer passion,
        @NotBlank @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리")
        String phoneNumber,
        @NotBlank @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$",
                message = "서류 제출 시간은 yyyy-MM-dd HH:mm 형식")
        String applicationTime
) { }
