package cotato.backend.dto.response;

import cotato.backend.domain.Admin;
import lombok.Getter;

@Getter
public class AdminResponse {

    private final Long adminId;
    private final String name;
    private final int age;
    private final String phoneNumber;
    private final String role;

    public AdminResponse(Admin admin) {
        this.adminId = admin.getAdminId();
        this.name = admin.getName();
        this.age = admin.getAge();
        this.phoneNumber = admin.getPhoneNumber();
        this.role = admin.getRole().name();
    }
}
