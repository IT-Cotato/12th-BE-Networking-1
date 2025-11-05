package cotato.backend.domain.user.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    // 지원자 나이 명세 (22~30)
    @Min(value = 22, message = "나이는 22~30세여야 합니다.")
    @Max(value = 30, message = "나이는 22~30세여야 합니다.")
    private Integer age;

    @NotBlank
    @Pattern(regexp = "^010[0-9]{8}$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
    private String phoneNumber;
}