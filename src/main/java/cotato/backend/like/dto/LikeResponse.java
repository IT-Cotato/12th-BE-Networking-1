package cotato.backend.like.dto;

import cotato.backend.domain.like.entity.ApplicationLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {
    private Long likeId;
    private Long applicationId;
    private Long staffId;
    private String staffName;

    public static LikeResponse from(ApplicationLike like) {
        return new LikeResponse(
                like.getId(),
                like.getApplication().getId(),
                like.getStaff().getId(),
                like.getStaff().getName()
        );
    }
}
