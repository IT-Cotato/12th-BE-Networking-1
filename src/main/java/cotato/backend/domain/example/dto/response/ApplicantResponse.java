package cotato.backend.domain.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ApplicantResponse {

    private String name;
    private Integer age;
    private String phoneNumber;
}
