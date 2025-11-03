package cotato.backend.domain.application.dto.response;

import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.application.entity.Part;

public record ApplicationListResponse(
        String name,
        int period,
        Part part,
        int likeCount // int -> Long
) {
    public static ApplicationListResponse from(ApplicationEntity application) {
        return new ApplicationListResponse(
                application.getApplicant().getName(),
                application.getPeriod(),
                application.getPart(),
                application.getLikeCount()
        );
    }
}
