package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.ApplicationForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApplicationFormListResponse {
    private String name;
    private Integer period;
    private ApplicationForm.Part part;
    private Integer likeCount;

}
