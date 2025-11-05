package cotato.backend.staff.dto;



import cotato.backend.domain.staff.entity.Staff;
import cotato.backend.domain.staff.entity.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private StaffRole role;

    public static StaffResponse from(Staff staff) {
        return new StaffResponse(
                staff.getId(),
                staff.getName(),
                staff.getAge(),
                staff.getPhoneNumber(),
                staff.getRole()
        );
    }
}
