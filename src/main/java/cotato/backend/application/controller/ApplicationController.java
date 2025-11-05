package cotato.backend.application.controller;

import cotato.backend.application.dto.ApplicationCreateRequest;
import cotato.backend.application.dto.ApplicationDetailResponse;
import cotato.backend.application.dto.ApplicationListRequest;
import cotato.backend.application.dto.ApplicationListResponse;
import cotato.backend.application.service.ApplicationService;
import cotato.backend.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public DataResponse<ApplicationDetailResponse> createApplication(@RequestBody ApplicationCreateRequest request) {
        ApplicationDetailResponse response = applicationService.createApplication(request);
        return DataResponse.created(response);
    }

    @GetMapping("/{applicationId}")
    public DataResponse<ApplicationDetailResponse> getApplicationDetail(@PathVariable Long applicationId) {
        ApplicationDetailResponse response = applicationService.getApplicationDetail(applicationId);
        return DataResponse.from(response);
    }

    @GetMapping("/list")
    public DataResponse<List<ApplicationListResponse>> getAllApplications(@RequestBody ApplicationListRequest request) {
        List<ApplicationListResponse> responses = applicationService.getApplicationList(request);
        return DataResponse.from(responses);
    }
}
