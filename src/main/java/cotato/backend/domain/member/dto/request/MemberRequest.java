package cotato.backend.domain.member.dto.request;

import cotato.backend.common.enums.Part;
import cotato.backend.domain.member.enums.Role;

public record MemberRequest(
        String name,
        Integer generation,
        Integer age,
        Part part,
        String phoneNum,
        Role role
) {
}
