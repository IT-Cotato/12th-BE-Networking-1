package cotato.backend.domain.applicant.dto.response;

public record LikeCountResponse(
        Long likeCount
) {
    public static LikeCountResponse of(Long likeCount) {
        return new LikeCountResponse(likeCount);
    }
}
