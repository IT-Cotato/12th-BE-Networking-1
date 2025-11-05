package cotato.backend.applicant.controller;

import cotato.backend.applicant.dto.ApplicantResponse;
import cotato.backend.applicant.dto.ApplicantUpdateRequest;
import cotato.backend.applicant.service.ApplicantService;
import cotato.backend.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/{applicantId}")
    public DataResponse<ApplicantResponse> getApplicant(@PathVariable Long applicantId){
        ApplicantResponse response = applicantService.getApplicant(applicantId);
        return DataResponse.from(response);
    }

    @PutMapping("/{applicantId}")
    public DataResponse<ApplicantResponse> updateApplicant(
            @PathVariable Long applicantId,
            @RequestBody ApplicantUpdateRequest request){
        ApplicantResponse response = applicantService.updateApplicant(applicantId, request);
        return DataResponse.from(response);
    }

}
