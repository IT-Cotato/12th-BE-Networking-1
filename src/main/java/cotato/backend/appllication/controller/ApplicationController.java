package cotato.backend.appllication.controller;

import cotato.backend.appllication.dto.ApplicationDetailResponseDto;
import cotato.backend.appllication.dto.ApplicationListResponseDto;
import cotato.backend.appllication.dto.ApplicationRequestDto;
import cotato.backend.appllication.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * 지원서 등록
     * (수정 및 삭제 불가)
     */
    @PostMapping
    public ResponseEntity<String> createApplication(@RequestBody ApplicationRequestDto request) {
        applicationService.createApplication(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("지원서가 성공적으로 등록되었습니다.");
    }

    /**
     * 지원서 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDetailResponseDto> getApplication(@PathVariable Long id) {
        ApplicationDetailResponseDto response = applicationService.getApplication(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 지원서 목록 조회
     * @param filter 필터링 기준 (generation / likes / mixed)
     * @param period 필터 기준이 generation 또는 mixed인 경우 필수
     */
    @GetMapping
    public ResponseEntity<List<ApplicationListResponseDto>> getApplications(
            @RequestParam(defaultValue = "generation") String filter,
            @RequestParam(required = false) Integer period) {
        List<ApplicationListResponseDto> list = applicationService.getApplications(filter, period);
        return ResponseEntity.ok(list);
    }

    /**
     * 좋아요 추가
     */
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeApplication(
            @PathVariable("id") Long applicationId,
            @RequestParam("managerId") Long managerId) {
        applicationService.likeApplication(applicationId, managerId);
        return ResponseEntity.ok("좋아요가 추가되었습니다.");
    }

    /**
     * 좋아요 취소
     */
    @DeleteMapping("/{id}/like")
    public ResponseEntity<String> unlikeApplication(
            @PathVariable("id") Long applicationId,
            @RequestParam("managerId") Long managerId) {
        applicationService.unlikeApplication(applicationId, managerId);
        return ResponseEntity.ok("좋아요가 취소되었습니다.");
    }
}
