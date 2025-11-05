package cotato.backend.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SortType {
    LIKES("좋아요순"),
    LATEST("최신순"),
    OLDEST("오래된순");

    private final String description;
}
