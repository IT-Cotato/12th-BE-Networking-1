package cotato.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    PRESIDENT("회장"),
    VICE_PRESIDENT("부회장"),
    PART_LEADER("파트장"),
    PLANNING_LEAD("기획팀장"),
    PROMOTION_LEAD("홍보팀장"),
    EDUCATION_LEAD("교육팀장");
    private final String description;
}
