package cotato.backend.domain.example.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequest {

    @NotNull(message = "운영진 ID가 입력되지 않았습니다.")
    private Long adminId;

    @NotNull(message = "지원서 ID가 입력되지 않았습니다.")
    private Long formId;
}
