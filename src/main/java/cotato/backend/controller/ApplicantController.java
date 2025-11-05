package cotato.backend.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.dto.request.ApplicantRequest;
import cotato.backend.dto.response.ApplicantResponse;
import cotato.backend.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applicant")
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/{applicantId}")
    public ResponseEntity<DataResponse<ApplicantResponse>> getApplicant(@PathVariable Long applicantId) {
        ApplicantResponse response = applicantService.getApplicant(applicantId);
        return ResponseEntity.ok(DataResponse.from(response));
    }

    @PutMapping("/{applicantId}")
    public ResponseEntity<DataResponse<Void>> updateApplicant(@PathVariable Long applicantId,
                                                              @RequestBody ApplicantRequest request) {
        applicantService.updateApplicant(applicantId, request);
        return ResponseEntity.ok(DataResponse.ok());
    }
}
