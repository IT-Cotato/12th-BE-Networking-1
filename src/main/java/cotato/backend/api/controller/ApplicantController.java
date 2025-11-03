package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantController {

    private final ApplicantService applicantService;

    // 테스트용
    @PostMapping
    public DataResponse<DefaultIdResponse> save(@RequestBody ApplicantRequest request) {
        Long id = applicantService.save(request).getId();
        return DataResponse.created(DefaultIdResponse.of(id));
    }

    @GetMapping("/{id}")
    public DataResponse<ApplicantResponse> findById(@PathVariable Long id) {
        return DataResponse.from(applicantService.findById(id));
    }

    @PutMapping("/{id}")
    public DataResponse<ApplicantResponse> update(@PathVariable Long id, @RequestBody ApplicantRequest request) {
        return DataResponse.from(applicantService.update(id, request));
    }
}
