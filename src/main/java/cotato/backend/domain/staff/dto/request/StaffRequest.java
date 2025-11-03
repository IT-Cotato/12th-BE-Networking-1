package cotato.backend.domain.staff.dto.request;

import cotato.backend.domain.staff.entity.Role;
import cotato.backend.domain.staff.entity.StaffEntity;

public record StaffRequest(
	String name,
    int age,
    String phoneNumber,
    Role role
) {
    public StaffEntity toEntity() {
        return StaffEntity.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
    }
}