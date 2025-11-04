package cotato.backend.manager.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerResponseDto {

    private Long id;
    private String name;
    private Integer birthYear;
    private String role;
    private String phoneNumber;
}
