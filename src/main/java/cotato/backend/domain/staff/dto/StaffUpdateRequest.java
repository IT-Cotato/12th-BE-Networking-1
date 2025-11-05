package cotato.backend.domain.staff.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StaffUpdateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Min(value = 20, message = "나이는 20세 이상이어야 합니다.")
    @Max(value = 70, message = "나이는 30세 이하여야 합니다.")
    private Integer age;

    @NotBlank
    @Pattern(regexp = "^010[0-9]{8}$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @NotBlank(message = "역할은 필수입니다.")
    private String role;
}
