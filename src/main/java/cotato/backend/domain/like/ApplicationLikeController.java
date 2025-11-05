package cotato.backend.domain.like;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applications")
public class ApplicationLikeController {

    private final ApplicationLikeService ApplicationLikeService;

    @PostMapping("/{applicationId}/likes")
    public ResponseEntity<Void> addLike(
            @PathVariable Long applicationId ) {
        Long tempStaffId = 1L;
        ApplicationLikeService.addLike(applicationId, tempStaffId);

        return ResponseEntity.ok(null);
    }
}