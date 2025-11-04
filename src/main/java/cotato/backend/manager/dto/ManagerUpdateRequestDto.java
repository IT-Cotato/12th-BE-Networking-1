package cotato.backend.manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerUpdateRequestDto {

    private String name;
    private Integer birthYear;
    private String role;
    private String phoneNumber;
}
