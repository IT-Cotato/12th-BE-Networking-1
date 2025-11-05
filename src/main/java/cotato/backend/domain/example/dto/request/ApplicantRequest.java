package cotato.backend.domain.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ApplicantRequest {

    @NotBlank(message = "이름이 입력되지 않았습니다.")
    private String name;

    @NotNull(message = "나이가 입력되지 않았습니다")
    @Min(value = 22)
    @Max(value = 30)
    private Integer age;

    @NotBlank
    @Pattern(regexp = "^010\\d{8}$", message = "전화번호는 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;
}
