package cotato.backend.domain.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManagerUpdateRequest {
    // 연락처, 역할만 수정 가능
    @NotBlank(message = "연락처는 필수 입력값입니다.")
    private String phoneNumber;

    @NotBlank(message = "역할은 필수 입력값입니다.")
    private String role;
}
