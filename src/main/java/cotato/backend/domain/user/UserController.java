package cotato.backend.domain.user;

import cotato.backend.domain.user.dto.UserResponse;
import cotato.backend.domain.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(
            @PathVariable Long userId
    ) {
        UserResponse response = userService.getUserInfo(userId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserInfo(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        UserResponse response = userService.updateUserInfo(userId, request);
        return ResponseEntity.ok(response);
    }
}