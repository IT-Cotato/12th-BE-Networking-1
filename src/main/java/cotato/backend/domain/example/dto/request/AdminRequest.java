package cotato.backend.domain.example.dto.request;

import cotato.backend.domain.example.entity.Admin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminRequest {


    @NotBlank(message = "이름이 입력되지 않았습니다.")
    private String name;

    @NotNull(message = "나이가 입력되지 않았습니다.")
    private Integer age;

    @NotNull(message = "휴대폰 번호가 입력되지 않았습니다.")
    @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;

    @NotNull(message = "운영진 역할이 입력되지 않았습니다.")
    private Admin.Role role;

}
