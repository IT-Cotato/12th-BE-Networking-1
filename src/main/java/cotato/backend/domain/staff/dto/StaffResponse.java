package cotato.backend.domain.staff.dto;

import cotato.backend.domain.staff.StaffDetail;
import cotato.backend.domain.user.User;
import lombok.Getter;

@Getter
public class StaffResponse {

    private final String name;
    private final int age;
    private final String phoneNumber;

    private final String role;

    public StaffResponse(User user, StaffDetail staffDetail) {
        this.name = user.getName();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
        this.role = staffDetail.getRoleCode().getDescription();
    }

    public StaffResponse(StaffDetail staffDetail) {
        this(staffDetail.getUser(), staffDetail);
    }
}