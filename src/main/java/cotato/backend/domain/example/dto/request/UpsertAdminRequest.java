package cotato.backend.domain.example.dto.request;

import cotato.backend.domain.example.entity.AdminRole;
import lombok.Data;

@Data
public class UpsertAdminRequest {
    private Long id; // null이면 생성, 값이 있으면 수정
    private String name;
    private Integer age;
    private String phoneNumber;
    private AdminRole role;
}


