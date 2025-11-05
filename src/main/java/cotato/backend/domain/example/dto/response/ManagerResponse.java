package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.CManager;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManagerResponse {
    private String name;
    private String role;
    private String phoneNumber;

    public static ManagerResponse of(CManager manager) {
        return ManagerResponse.builder()
                .name(manager.getName())
                .role(String.valueOf(manager.getRole().getKoreanName()))
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }
}
