package cotato.backend.staff.dto;

import cotato.backend.domain.staff.entity.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StaffCreateRequest {
    private String name;
    private Integer age;
    private String phoneNumber;
    private StaffRole role;
}
