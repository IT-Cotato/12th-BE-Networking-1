package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicationFormLikeService;
import cotato.backend.domain.applicant.dto.response.LikeCountResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application-forms/{applicationFormId}/likes")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationFormLikeController {

    private final ApplicationFormLikeService applicationFormLikeService;

    @PostMapping
    public DataResponse<Void> addLike(
            @PathVariable Long applicationFormId,
            @RequestParam Long memberId // TODO: Spring Security 적용 시 Authentication으로 대체
    ) {
        applicationFormLikeService.addLike(memberId, applicationFormId);
        return DataResponse.ok();
    }

    @DeleteMapping
    public DataResponse<Void> deleteLike(@PathVariable Long applicationFormId, @RequestParam Long memberId) {
        applicationFormLikeService.deleteLike(memberId, applicationFormId);
        return DataResponse.ok();
    }

    @GetMapping
    public DataResponse<LikeCountResponse> getLikeCount(@PathVariable Long applicationFormId) {
       return DataResponse.from(applicationFormLikeService.getLikeCount(applicationFormId));
    }
}
