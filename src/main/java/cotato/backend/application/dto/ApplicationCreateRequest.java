package cotato.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreateRequest {
    private String name;
    private Integer period;
    private Integer age;
    private String part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;
}
