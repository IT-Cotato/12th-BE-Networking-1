package cotato.backend.domain.staff.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;

import java.util.Arrays;

public enum Role {
    PARTJANG("파트장"),
    PLANNING_LEADER("기획팀장"),
    PR_LEADER("홍보팀장"),
    VICE_PRESIDENT("부회장"),
    PRESIDENT("회장"),
    EDUCATION_LEADER("교육팀장");

    @JsonValue
    private final String korean;

    Role(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return this.korean;
    }

    // 한국어 입력값을 enum 값으로 변환
    @JsonCreator
    public static Role fromString(String value) {
        return Arrays.stream(Role.values())
                .filter(role -> role.korean.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_STAFF_ROLE));
    }
}
