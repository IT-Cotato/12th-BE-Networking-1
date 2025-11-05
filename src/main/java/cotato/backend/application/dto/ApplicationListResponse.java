package cotato.backend.application.dto;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationListResponse {
    private Long id;
    private String name;
    private Integer period;
    private Part part;
    private Integer likeCount;

    public static ApplicationListResponse from(Application application) {
        return new ApplicationListResponse(
                application.getId(),
                application.getApplicant().getName(),
                application.getPeriod(),
                application.getPart(),
                application.getLikeCount()
        );
    }
}
