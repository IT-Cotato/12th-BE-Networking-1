package cotato.backend.api.dto.response;

import cotato.backend.domain.example.entity.Part;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ApplicationListItem {
    private Long id;
    private String name;
    private Integer period;
    private Part part;
    private long likes;
}