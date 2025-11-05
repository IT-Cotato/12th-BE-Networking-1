package cotato.backend.domain.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StaffRole {
    PART_LEAD("파트장"),
    PM_LEAD("기획팀장"),
    PR_LEAD("홍보팀장"),
    VICE("부회장"),
    PRESIDENT("회장"),
    EDU_LEAD("교육팀장");

    private final String description;
}
