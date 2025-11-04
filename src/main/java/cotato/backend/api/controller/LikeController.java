package cotato.backend.api.controller;

import cotato.backend.domain.example.dto.response.LikeListResponse;
import cotato.backend.domain.example.application.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // 서류에 좋아요 추가
    @PatchMapping("/{applicationId}/like/{managerId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long applicationId, @PathVariable Long managerId) {
        boolean isLiked = likeService.toggleLike(applicationId, managerId);

        if (isLiked) {
            return ResponseEntity.ok("좋아요가 등록되었습니다.");
        } else {
            return ResponseEntity.ok("좋아요가 취소되었습니다.");
        }
    }

    // 해당 서류에 좋아요 누른 운영진 이름 조회
    @GetMapping("/{applicationId}/likes")
    public List<LikeListResponse> getLikesByApplication(@PathVariable Long applicationId) {
        return likeService.getLikesByApplication(applicationId);
    }
}
