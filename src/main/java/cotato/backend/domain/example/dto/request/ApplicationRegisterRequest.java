package cotato.backend.domain.example.dto.request;

//import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // <- 명시적으로 지정
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
//import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
public class ApplicationRegisterRequest {
    // 이름
    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(min=2, max=10, message = "이름은 2글자 이상, 10글자 이하여야 합니다.")
    private String name;

    // 지원 기수
    @NotNull(message = "지원 기수는 필수입니다.")
    @Min(value=1, message = "지원 기수는 1 이상이어야 합니다.")
    private Integer period;

    // 나이(22~30)
    @NotNull(message = "나이는 필수 입력값입니다.")
    @Min(value=22, message = "나이는 22세 이상이어야 합니다.")
    @Max(value=30, message = "나이는 30세 이하여야 합니다.")
    private Integer age;

    // 지원 파트
    @NotBlank(message = "지정된 파트 중 하나를 선택해야 합니다.")
    @Pattern(regexp = "기획|디자이너|프론트엔드|백엔드", message = "유효하지 않은 파트입니다.")
    private String part;

    // 실력
    @NotNull(message = "실력 점수는 필수입니다.")
    @Min(value=0, message = "실력은 0 이상이어야 합니다.")
    @Max(value=10, message = "실력은 10 이하여야 합니다.")
    private Integer ability;

    // 열정
    @NotNull(message = "열정 점수는 필수입니다.")
    @Min(value=0, message = "열정은 0 이상이어야 합니다.")
    @Max(value=10, message = "열정은 10 이하여야 합니다.")
    private Integer passion;

    // 폰번호
    @NotBlank(message = "휴대폰 번호는 필수 입력값입니다.")
    @Pattern(regexp = "^010[0-9]{8}$", message = "휴대폰 번호 형식이 올바르지 않습니다. (010으로 시작하는 11자리)")
    private String phoneNumber;

    // 서류 제출 시간
    @NotNull(message = "서류 제출 시간은 필수입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime applicationTime;
}
