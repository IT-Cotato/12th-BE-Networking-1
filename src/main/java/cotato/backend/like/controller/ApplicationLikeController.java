package cotato.backend.like.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.like.dto.LikeResponse;
import cotato.backend.like.service.ApplicationLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationLikeController {

    private final ApplicationLikeService applicationLikeService;

    @PostMapping("/{applicationId}/likes")
    public DataResponse<LikeResponse> addLike(
            @PathVariable Long applicationId,
            @RequestParam Long staffId
    ) {
        LikeResponse response = applicationLikeService.addLike(applicationId, staffId);
        return DataResponse.created(response);
    }

    @DeleteMapping("/{applicationId}/likes")
    public DataResponse<Void> removeLike(
            @PathVariable Long applicationId,
            @RequestParam Long staffId
    ) {
        applicationLikeService.removeLike(applicationId, staffId);
        return DataResponse.from(null);
    }

}
