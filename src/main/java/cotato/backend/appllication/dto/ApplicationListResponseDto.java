package cotato.backend.appllication.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationListResponseDto {
    private String name;
    private int generation;
    private String part;
    private long likeCount;
}
