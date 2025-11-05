package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.Application;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationListResponse {
    private Long applicationId;
    private String applicantName;
    private Integer period;
    private String part;
    private Integer likesCount;

    public static ApplicationListResponse of(Application application) {
        String name = application.getApplicant() != null ? application.getApplicant().getName() : "N/A";

        return ApplicationListResponse.builder()
                .applicationId(application.getApplicationId())
                .applicantName(name)
                .period(application.getPeriod())
                .part(application.getPart().getKoreanName())
                .likesCount(application.getLikesCount())
                .build();
    }
}
