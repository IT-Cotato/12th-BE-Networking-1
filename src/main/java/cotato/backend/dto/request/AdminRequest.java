package cotato.backend.dto.request;

import cotato.backend.domain.Admin;
import cotato.backend.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminRequest {

    private String name;
    private int age;
    private String phoneNumber;
    private String role;

    public Admin toEntity() {
        return Admin.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .role(Role.valueOf(role.toUpperCase()))
                .build();
    }
}
