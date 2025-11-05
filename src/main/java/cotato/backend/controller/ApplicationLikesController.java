package cotato.backend.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.dto.request.ApplicationLikesRequest;
import cotato.backend.dto.response.ApplicationLikesListResponse;
import cotato.backend.service.ApplicationLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationLikesController {

    private final ApplicationLikesService likesService;

    @PostMapping("/{applicationId}/likes")
    public ResponseEntity<DataResponse<Void>> likeApplication(@PathVariable Long applicationId,
                                                              @RequestBody ApplicationLikesRequest request) {
        request.setApplicationId(applicationId);
        likesService.likeApplication(request);
        return ResponseEntity.ok(DataResponse.ok());
    }

    @GetMapping("/{applicationId}/likes")
    public ResponseEntity<DataResponse<ApplicationLikesListResponse>> getLikes(@PathVariable Long applicationId) {
        ApplicationLikesListResponse response = likesService.getLikes(applicationId);
        return ResponseEntity.ok(DataResponse.from(response));
    }
}
