package cotato.backend.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ApplicationLikesRequest {

    private Long adminId;
    @Setter
    private Long applicationId;

}
