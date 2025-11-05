package cotato.backend.domain.example.dto.response;

import cotato.backend.domain.example.entity.CManager;
import cotato.backend.domain.example.entity.Like;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeListResponse {
    private Long managerId;
    private String managerName;

    public static LikeListResponse of(Like like) {
        CManager manager = like.getCManager();

        return LikeListResponse.builder()
                .managerId(manager.getManagerId())
                .managerName(manager.getName())
                .build();
    }
}
