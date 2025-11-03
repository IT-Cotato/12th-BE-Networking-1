package cotato.backend.application.dto;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDetailResponse {
    private Long id;
    private String name;
    private Integer period;
    private Integer age;
    private Part part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;

    public static ApplicationDetailResponse from(Application application) {
        return new ApplicationDetailResponse(
                application.getId(),
                application.getApplicant().getName(),
                application.getPeriod(),
                application.getApplicant().getAge(),
                application.getPart(),
                application.getAbility(),
                application.getPassion(),
                application.getApplicant().getPhoneNumber(),
                application.getApplicationTime()
        );
    }
}
