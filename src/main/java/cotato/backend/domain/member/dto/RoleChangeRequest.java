package cotato.backend.domain.member.dto;

import cotato.backend.domain.member.enums.Role;

public record RoleChangeRequest(
        Role role
) {
}
