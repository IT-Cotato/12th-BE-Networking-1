package cotato.backend.domain.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.application.entity.ApplicationPart;

import java.time.LocalDateTime;

public record ApplicationDetailResponse(
        String name,
        int period,
        int age,
        ApplicationPart part,
        int ability,
        int passion,
        String phoneNumber,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
        LocalDateTime applicationTime
) {
	public static ApplicationDetailResponse from(ApplicationEntity application) {
		return new ApplicationDetailResponse(
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
