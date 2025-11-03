package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicationFormService;
import cotato.backend.domain.applicant.dto.request.ApplicationFormRequest;
import cotato.backend.domain.applicant.dto.request.ApplicationFormStatusUpdateRequest;
import cotato.backend.domain.applicant.dto.response.ApplicationFormResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application-forms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @PostMapping
    public DataResponse<DefaultIdResponse> submit(@Valid @RequestBody ApplicationFormRequest request) {
        Long id = applicationFormService.submit(request);
        return DataResponse.created(DefaultIdResponse.of(id));
    }

    @GetMapping("/{id}")
    public DataResponse<ApplicationFormResponse> findById(@PathVariable Long id) {
        return DataResponse.from(applicationFormService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public DataResponse<Void> updateStatus(@PathVariable Long id, @Valid @RequestBody ApplicationFormStatusUpdateRequest request) {
        applicationFormService.updateStatus(id, request.status());
        return DataResponse.ok();
    }
}
