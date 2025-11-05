package cotato.backend.api.controller;

import cotato.backend.domain.example.dto.response.ApplicationDetailResponse;
import cotato.backend.domain.example.dto.response.ApplicationListResponse;
import cotato.backend.domain.example.dto.request.ApplicationRegisterRequest;
import cotato.backend.domain.example.application.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    // 지원자 서류 등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long registerApplication(@RequestBody @Valid ApplicationRegisterRequest request) {
        return applicationService.registerApplication(request);
    }

    // 지원자 서류 상세 조회
    @GetMapping("/{applicationId}")
    public ApplicationDetailResponse getApplicationDetail(@PathVariable Long applicationId) {
        return applicationService.getApplicationDetail(applicationId);
    }

    // 지원 서류 리스트 조회
    @GetMapping
    public List<ApplicationListResponse> getApplicationList(
            // 쿼리 파라미터
            @RequestParam(required = false, defaultValue = "period") String filterBy, // "likes", "gisu", "gisu+likes"
            @RequestParam(required = false, defaultValue = "1") int page, // 페이지 번호 (1~...)
            @RequestParam(required = false, defaultValue = "10") int pageSize // 페이지 사이즈
    ) {
        return applicationService.getApplicationList(filterBy, page, pageSize);
    }
}
