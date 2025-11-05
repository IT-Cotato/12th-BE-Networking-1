package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.Application;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationDetailResponse {
    // id, 이름, 기수, 나이, 파트, 실력, 열정, 연락처, 제출시간
    private Long applicationId;
    private String applicantName;
    private Integer period;
    private Integer age;
    private String part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private String applicationTime;

    public static ApplicationDetailResponse of(Application application) {
        if (application.getApplicant() == null) {
            throw new IllegalStateException("Application 엔티티에 Applicant 정보가 로드되지 않았습니다.");
        }

        return ApplicationDetailResponse.builder()
                .applicationId(application.getApplicationId())
                .applicantName(application.getApplicant().getName())
                .period(application.getPeriod())
                .age(application.getApplicant().getAge())
                .part(application.getPart().getKoreanName())
                .ability(application.getAbility())
                .passion(application.getPassion())
                .phoneNumber(application.getApplicant().getPhoneNumber())
                .applicationTime(application.getApplicationTime().toString())
                .build();
    }
}
