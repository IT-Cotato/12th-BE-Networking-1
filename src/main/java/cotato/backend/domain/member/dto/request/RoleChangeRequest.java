package cotato.backend.domain.member.dto.request;

import cotato.backend.domain.member.enums.Role;

public record RoleChangeRequest(
        Role role
) {
}
