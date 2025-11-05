package cotato.backend.dto.response;

import cotato.backend.domain.ApplicationLikes;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ApplicationLikesListResponse {

    private final Long applicationId;
    private final int likesCount;
    private final List<LikedAdmin> likedBy;

    public ApplicationLikesListResponse(Long applicationId, List<ApplicationLikes> likes) {
        this.applicationId = applicationId;
        this.likesCount = likes.size();
        this.likedBy = likes.stream()
                .map(like -> new LikedAdmin(
                        like.getAdmin().getAdminId(),
                        like.getAdmin().getName(),
                        like.getAdmin().getRole().name()
                ))
                .collect(Collectors.toList());
    }

    @Getter
    public static class LikedAdmin {
        private final Long adminId;
        private final String name;
        private final String role;

        public LikedAdmin(Long adminId, String name, String role) {
            this.adminId = adminId;
            this.name = name;
            this.role = role;
        }
    }
}
