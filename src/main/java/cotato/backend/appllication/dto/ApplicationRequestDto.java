package cotato.backend.appllication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApplicationRequestDto {

    private String name;
    private int generation;
    private int birthYear;
    private String part;
    private int ability;
    private int passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;
}
