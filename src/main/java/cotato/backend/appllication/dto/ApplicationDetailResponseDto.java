package cotato.backend.appllication.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApplicationDetailResponseDto {

    private String name;
    private int generation;
    private int birthYear;
    private String part;
    private int ability;
    private int passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;
}
