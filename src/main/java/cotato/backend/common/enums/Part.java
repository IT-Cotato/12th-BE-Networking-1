package cotato.backend.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Part {
    PLANNING("기획"),
    DESIGN("디자이너"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    private final String description;
}
