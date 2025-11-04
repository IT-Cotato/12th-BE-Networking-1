package cotato.backend.domain.member.dto;

import cotato.backend.common.enums.Part;
import cotato.backend.domain.member.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberRequest(
        String name,
        Integer generation,
        Integer age,
        Part part,
        String phoneNum,
        Role role
) {
}
