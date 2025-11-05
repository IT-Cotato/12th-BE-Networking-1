package cotato.backend.dto.response;


import cotato.backend.domain.ApplicationLikes;
import lombok.Getter;

@Getter
public class ApplicationLikesResponse {

    private final Long likeId;
    private final Long applicationId;
    private final Long adminId;
    private final String adminName;
    private final String adminRole;

    public ApplicationLikesResponse(ApplicationLikes like) {
        this.likeId = like.getLikeId();
        this.applicationId = like.getApplication().getApplicationId();
        this.adminId = like.getAdmin().getAdminId();
        this.adminName = like.getAdmin().getName();
        this.adminRole = like.getAdmin().getRole().name();
    }
}
