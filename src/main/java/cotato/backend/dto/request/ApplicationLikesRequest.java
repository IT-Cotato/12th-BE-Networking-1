package cotato.backend.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationLikesRequest {

    private Long adminId;
    private Long applicationId;

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
