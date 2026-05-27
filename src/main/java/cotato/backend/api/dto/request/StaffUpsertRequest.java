package cotato.backend.api.dto.request;

import cotato.backend.domain.example.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StaffUpsertRequest {
    @NotBlank @Pattern(regexp="^[가-힣]{2,10}$")
    private String name;

    @NotNull @Min(22) @Max(60)
    private Integer age;

    @NotBlank @Pattern(regexp="^010\\d{8}$")
    private String phone;

    @NotNull
    private Role role;
}