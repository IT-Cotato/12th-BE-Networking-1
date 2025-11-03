package cotato.backend.domain.application.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;

import java.util.Arrays;

public enum Part {
    PLANNER("기획"),
    DESIGNER("디자이너"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    @JsonValue
    private final String korean;

    Part(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return this.korean;
    }

    // 한국어 입력값을 enum 값으로 변환
    @JsonCreator
    public static Part fromString(String value) {
        return Arrays.stream(Part.values())
                .filter(part -> part.korean.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PARAMETER));
    }

}

