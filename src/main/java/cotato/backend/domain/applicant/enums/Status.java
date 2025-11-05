package cotato.backend.domain.applicant.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Status {
    PENDING("심사중"),
    ACCEPTED("합격"),
    REJECTED("불합격");

    private final String description;
}
