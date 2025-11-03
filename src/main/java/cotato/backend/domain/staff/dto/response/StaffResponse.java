package cotato.backend.domain.staff.dto.response;

import cotato.backend.domain.staff.entity.Role;
import cotato.backend.domain.staff.entity.StaffEntity;

public record StaffResponse(
	Long id,
	String name,
    int age,
    String phoneNumber,
    Role role
) {
	public static StaffResponse from(StaffEntity staff) {
		return new StaffResponse(
			staff.getId(),
			staff.getName(),
            staff.getAge(),
            staff.getPhoneNumber(),
            staff.getRole()
		);
	}
}
