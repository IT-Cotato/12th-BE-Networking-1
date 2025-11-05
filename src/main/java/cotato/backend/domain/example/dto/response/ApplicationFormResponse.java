package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.ApplicationForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class ApplicationFormResponse {


    private Long id;
    private String name;
    private Integer period;
    private Integer age;
    private ApplicationForm.Part part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;

}
