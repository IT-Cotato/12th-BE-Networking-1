package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.application.application.ApplicationService;
import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationDetailResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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

}
