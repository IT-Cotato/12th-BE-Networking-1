package cotato.backend.domain.example.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.example.entity.ApplicationForm;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
// JSON 요청 바디를 객체로 역직렬화할때 필수 (Jackson 빈 객체 필드 주입)
@NoArgsConstructor
public class ApplicationFormRequest {

    @NotBlank(message = "이름이 입력되지 않았습니다.")
    @Size(min = 2,max = 10, message = "이름은 2글자 이상, 10글자 이하여야 합니다.")
    private String name;

    @NotNull(message = "지원 기수가 입력되지 않았습니다.")
    @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
    private Integer period;

    @NotNull(message = "나이가 입력되지 않았습니다.")
    @Min(value = 22, message = "나이는 22살 이상, 30살 이하여야 합니다.")
    @Max(value = 30, message = "나이는 22살 이상, 30살 이하여야 합니다.")
    private Integer age;

    @NotNull(message = "지원 파트가 입력되지 않았습니다.")
    private ApplicationForm.Part part;

    @NotNull(message = "실력 점수가 입력되지 않았습니다.")
    @Min(value = 0, message = "실력 점수는 0 이상 10 이하여야 합니다.")
    @Max(value = 10, message = "실력 점수는 0 이상 10 이하여야 합니다.")
    private Integer ability;

    @NotNull(message = "열정 점수가 입력되지 않았습니다.")
    @Min(value = 0, message = "열정 점수는 0 이상 10 이하여야 합니다.")
    @Max(value = 10, message = "열정 점수는 0 이상 10 이하여야 합니다.")
    private Integer passion;

    @NotBlank(message = "핸드폰번호가 입력되지 않았습니다.")
    @Pattern(regexp = "^010\\d{8}$", message = "전화번호는 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;

    @NotNull(message = "서류 제출 시간이 입력되지 않았습니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime applicationTime;
}
