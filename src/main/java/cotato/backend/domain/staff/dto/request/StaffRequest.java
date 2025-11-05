package cotato.backend.domain.staff.dto.request;

import cotato.backend.domain.staff.entity.StaffRole;
import cotato.backend.domain.staff.entity.StaffEntity;

public record StaffRequest(
	String name,
    int age,
    String phoneNumber,
    StaffRole role
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