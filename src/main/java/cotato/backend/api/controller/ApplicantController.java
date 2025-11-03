package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applicant")
public class ApplicantController {
    private final ApplicantService applicantService;

    // 지원자 조회
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicantResponse>> getStaff(@PathVariable Long id) {
        ApplicantResponse response = applicantService.getApplicant(id);
        return ResponseEntity.ok(DataResponse.from(response));
    }

    // 지원자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicantResponse>> updateStaff(@PathVariable Long id, @RequestBody ApplicantRequest request) {
        ApplicantResponse response = applicantService.updateApplicant(id, request);
        return ResponseEntity.ok(DataResponse.from(response));
    }
}
