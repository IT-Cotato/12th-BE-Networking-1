package cotato.backend.likes.controller;

import cotato.backend.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    /**
     * 좋아요 추가
     * 예: POST /likes?applicationId=1&managerId=2
     */
    @PostMapping
    public ResponseEntity<String> addLike(
            @RequestParam Long applicationId,
            @RequestParam Long managerId
    ) {
        likesService.addLike(applicationId, managerId);
        return ResponseEntity.ok("좋아요가 추가되었습니다.");
    }

    /**
     * 좋아요 취소
     * 예: DELETE /likes?applicationId=1&managerId=2
     */
    @DeleteMapping
    public ResponseEntity<String> removeLike(
            @RequestParam Long applicationId,
            @RequestParam Long managerId
    ) {
        likesService.removeLike(applicationId, managerId);
        return ResponseEntity.ok("좋아요가 취소되었습니다.");
    }
}
