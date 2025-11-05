package cotato.backend.dto.request;

import cotato.backend.domain.Applicant;
import cotato.backend.domain.Application;
import cotato.backend.domain.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApplicationRequest {

    private String name;
    private int period;
    private int age;
    private String part;
    private int ability;
    private int passion;
    private String phoneNumber;

    // 지원자 생성 연동
    public Applicant toEntityApplicant() {
        return Applicant.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .build();
    }

    // 지원서 생성
    public Application toEntity(Applicant applicant) {
        return Application.builder()
                .applicant(applicant)
                .name(name)
                .period(period)
                .age(age)
                .part(Part.valueOf(part.toUpperCase()))
                .ability(ability)
                .passion(passion)
                .phoneNumber(phoneNumber)
                .likesNum(0)
                .build();
    }
}
