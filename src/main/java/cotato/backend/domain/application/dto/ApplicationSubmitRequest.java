package cotato.backend.domain.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class ApplicationSubmitRequest {

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, max = 10, message = "이름은 2~10자여야 합니다.")
    private String name;

    @NotNull(message = "기수는 필수입니다.")
    @Min(value = 1, message = "기수는 1 이상이어야 합니다.")
    private Integer period;

    @NotNull(message = "나이는 필수입니다.")
    @Min(value = 22, message = "나이는 22~30세여야 합니다.")
    @Max(value = 30, message = "나이는 22~30세여야 합니다.")
    private Integer age;

    @NotBlank(message = "파트는 필수입니다.")
    private String part; // "기획", "디자이너", "프론트엔드", "백엔드"

    @NotNull
    @Min(0) @Max(10)
    private Integer ability;

    @NotNull
    @Min(0) @Max(10)
    private Integer passion;

    @NotBlank
    @Pattern(regexp = "^010[0-9]{8}$", message = "휴대폰 번호 형식이 올바르지 않습니다. (01012345678)")
    private String phoneNumber;

    @NotBlank(message = "제출 시간은 필수입니다.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "시간 형식이 올바르지 않습니다. (yyyy-MM-dd HH:mm)")
    private String applicationTime; // "2025-02-28 23:30"
}