package cotato.backend.api.controller;

import cotato.backend.domain.example.application.LikeService;
import cotato.backend.domain.example.dto.request.LikeToggleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Long> like(@RequestBody LikeToggleRequest request) {
        Long id = likeService.like(request.getAdminId(), request.getApplicationId());
        return ResponseEntity.ok(id);
    }

    @DeleteMapping
    public ResponseEntity<Void> unlike(@RequestBody LikeToggleRequest request) {
        likeService.unlike(request.getAdminId(), request.getApplicationId());
        return ResponseEntity.noContent().build();
    }
}


