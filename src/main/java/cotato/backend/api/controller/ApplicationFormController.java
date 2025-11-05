package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.common.enums.SortType;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.application.ApplicationFormService;
import cotato.backend.domain.applicant.dto.request.ApplicationFormRequest;
import cotato.backend.domain.applicant.dto.request.ApplicationFormStatusUpdateRequest;
import cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse;
import cotato.backend.domain.applicant.dto.response.ApplicationFormResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application-forms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @PostMapping
    public DataResponse<DefaultIdResponse> submit(@RequestBody ApplicationFormRequest request) {
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

    @GetMapping
    public DataResponse<Page<ApplicationFormListResponse>> getApplicationForms(
            @RequestParam(required = false) Integer generation,
            @RequestParam(required = false) SortType sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (generation != null && sortBy != null) {
            return DataResponse.from(
                    applicationFormService.findByGenerationAndSort(generation, sortBy, page, size)
            );
        }

        if (generation != null) {
            return DataResponse.from(
                    applicationFormService.findByGeneration(generation, page, size)
            );
        }

        if (sortBy != null) {
            return DataResponse.from(
                    applicationFormService.findBySort(sortBy, page, size)
            );
        }

        // 필터 없음 - 에러
        throw new AppException(ErrorCode.INVALID_PARAMETER);
    }
}
