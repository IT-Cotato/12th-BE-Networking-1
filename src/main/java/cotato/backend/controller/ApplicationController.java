package cotato.backend.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.dto.response.ApplicationListResponse;
import cotato.backend.dto.response.ApplicationResponse;
import cotato.backend.dto.response.DefaultIdResponse;
import cotato.backend.service.ApplicationService;
import cotato.backend.dto.request.ApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<DataResponse<DefaultIdResponse>> createApplication(@RequestBody ApplicationRequest request) {
        Long id = applicationService.createApplication(request);
        return ResponseEntity.ok(DataResponse.from(DefaultIdResponse.of(id)));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<DataResponse<ApplicationResponse>> getApplication(@PathVariable Long applicationId) {
        ApplicationResponse response = applicationService.getApplication(applicationId);
        return ResponseEntity.ok(DataResponse.from(response));
    }

    @GetMapping("/list")
    public ResponseEntity<DataResponse<ApplicationListResponse>> getApplicationList(
            @RequestParam(defaultValue = "period") String filterBy,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        ApplicationListResponse response = applicationService.getApplications(filterBy, page, pageSize);
        return ResponseEntity.ok(DataResponse.from(response));
    }
}
