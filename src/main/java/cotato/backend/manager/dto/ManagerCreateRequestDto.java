package cotato.backend.manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerCreateRequestDto {

    private String name;
    private Integer birthYear;
    private String role;
    private String phoneNumber;
}
