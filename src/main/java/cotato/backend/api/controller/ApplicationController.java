package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.application.application.ApplicationService;
import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationDetailResponse;
import cotato.backend.domain.application.dto.response.ApplicationListResponse;
import cotato.backend.domain.like.dto.request.LikeRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    // 지원서 생성
    @PostMapping
    public ResponseEntity<DataResponse<Long>> createApplication(@Valid @RequestBody ApplicationRequest request) {
        Long id = applicationService.createApplication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                DataResponse.created(id)
        );
    }

    // 지원서 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicationDetailResponse>> getApplicationDetail(@PathVariable("id") Long id) {
        ApplicationDetailResponse response = applicationService.getApplicationDetail(id);
        return ResponseEntity.ok(DataResponse.from(response));
    }

    // 지원서 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<DataResponse<Page<ApplicationListResponse>>> getApplicationList(
            @RequestParam(required = false) String filterBy,
            @RequestParam(required = false) Integer period,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<ApplicationListResponse> listPage = applicationService.getApplicationList(filterBy, period, page, pageSize);

        return ResponseEntity.ok(DataResponse.from(listPage));
    }

    // 지원서 좋아요
    @PostMapping("/{id}/like")
    public ResponseEntity<DataResponse<Void>> likeApplication(@PathVariable("id") Long applicationId, @Valid @RequestBody LikeRequest request) {

        applicationService.likeApplication(applicationId, request.staffId());
        return ResponseEntity.ok(DataResponse.ok());
    }

}
