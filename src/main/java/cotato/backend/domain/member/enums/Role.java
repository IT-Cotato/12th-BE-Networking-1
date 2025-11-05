package cotato.backend.domain.member.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Role {
    MEMBER("일반 회원"),
    PART_LEADER("파트장"),
    PLANNING_LEADER("기획팀장"),
    PR_LEADER("홍보팀장"),
    EDUCATION_LEADER("교육팀장"),
    VICE_PRESIDENT("부회장"),
    PRESIDENT("회장)");

    private final String description;
}
