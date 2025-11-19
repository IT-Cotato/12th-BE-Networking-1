package cotato.backend.domain.example.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicantResponse {
    private Long id;
    private String name;
    private int age;
    private String phoneNumber;
}
