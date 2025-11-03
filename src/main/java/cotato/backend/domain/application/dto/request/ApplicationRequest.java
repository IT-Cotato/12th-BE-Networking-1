package cotato.backend.domain.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.application.entity.Part;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ApplicationRequest(
        @Size(min = 2, max = 10, message= "이름은 2글자 이상, 10글자 이하여야 합니다")
        String name,

        @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다")
        int period,

        @Min(value = 22, message = "나이는 22살 이상이어야 합니다")
        @Max(value = 30, message = "나이는 30살 이하여야 합니다")
        int age,

        int grade,
        Part part,

        @Min(value = 0, message = "실력은 0 이상이어야 합니다")
        @Max(value = 10, message = "실력은 10 이하여야 합니다")
        int ability,

        @Min(value = 0, message = "열정은 0 이상이어야 합니다")
        @Max(value = 10, message = "열정은 10 이하여야 합니다")
        int passion,

        @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리여야 합니다")
        String phoneNumber,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime applicationTime
) {
    // 지원자 엔티티
    public ApplicantEntity toApplicantEntity() {
        return ApplicantEntity.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .build();
    }

    public ApplicationEntity toApplicationEntity(ApplicantEntity applicant) {
        return ApplicationEntity.builder()
                .period(period)
                .part(part)
                .ability(ability)
                .passion(passion)
                .applicationTime(applicationTime)
                .applicant(applicant)
                .build();
    }
}
