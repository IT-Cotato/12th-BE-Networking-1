package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.application.LikeService;
import cotato.backend.domain.example.dto.request.LikeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public DataResponse<Void> likeForm(@Valid @RequestBody LikeRequest req) {
        likeService.likeForm(req);
        return DataResponse.ok();
    }

}
