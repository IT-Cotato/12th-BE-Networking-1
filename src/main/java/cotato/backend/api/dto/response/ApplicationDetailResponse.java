package cotato.backend.api.dto.response;

import cotato.backend.domain.example.entity.Part;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class ApplicationDetailResponse {
    private Long id;
    private String name;
    private Integer period;
    private Integer age;
    private Part part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private LocalDateTime applicationTime;
    private long likes;
}