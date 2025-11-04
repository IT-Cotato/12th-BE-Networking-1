package cotato.backend.manager.controller;

import cotato.backend.manager.dto.ManagerCreateRequestDto;
import cotato.backend.manager.dto.ManagerResponseDto;
import cotato.backend.manager.dto.ManagerUpdateRequestDto;
import cotato.backend.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 운영진 등록
     */
    @PostMapping
    public ResponseEntity<ManagerResponseDto> createManager(@RequestBody ManagerCreateRequestDto request) {
        ManagerResponseDto response = managerService.createManager(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 운영진 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponseDto> getManager(@PathVariable Long id) {
        ManagerResponseDto response = managerService.getManager(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 전체 운영진 조회
     */
    @GetMapping
    public ResponseEntity<List<ManagerResponseDto>> getAllManagers() {
        List<ManagerResponseDto> list = managerService.getAllManagers();
        return ResponseEntity.ok(list);
    }

    /**
     * 운영진 정보 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateManager(
            @PathVariable Long id,
            @RequestBody ManagerUpdateRequestDto request) {

        managerService.updateManager(id, request);
        return ResponseEntity.ok("운영진 정보가 수정되었습니다.");
    }

    /**
     * 운영진 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("운영진 정보가 삭제되었습니다.");
    }
}
