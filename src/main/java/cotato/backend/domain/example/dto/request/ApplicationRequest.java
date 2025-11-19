package cotato.backend.domain.example.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationRequest {
    private String name;
    private int period;
    private int age;
    private String part;
    private int ability;
    private int passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;
}
