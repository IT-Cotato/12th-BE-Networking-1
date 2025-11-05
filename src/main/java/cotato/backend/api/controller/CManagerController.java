package cotato.backend.api.controller;

import cotato.backend.domain.example.dto.request.ManagerUpdateRequest;
import cotato.backend.domain.example.dto.response.ManagerResponse;
import cotato.backend.domain.example.application.CManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class CManagerController {
    private final CManagerService cManagerService;

    // 운영진 정보 조회
    @GetMapping("/{managerId}")
    public ManagerResponse getManagerDetail(@PathVariable Long managerId) {
        return cManagerService.getManagerDetail(managerId);
    }

    // 운영진 정보 수정 patch로
    @PatchMapping("/{managerId}")
    public ResponseEntity<ManagerResponse> updateManager(
            @PathVariable Long managerId,
            @RequestBody ManagerUpdateRequest request) {

        ManagerResponse response = cManagerService.updateManager(managerId, request);
        return ResponseEntity.ok(response);
    }
}
