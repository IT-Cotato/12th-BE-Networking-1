package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.Admin;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminResponse {

    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private Admin.Role role;

}
