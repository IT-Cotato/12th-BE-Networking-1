package cotato.backend.api.controller;

import cotato.backend.domain.example.dto.response.ApplicantResponse;
import cotato.backend.domain.example.application.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

    // 지원자 상세 정보 조회
    @GetMapping("/{applicantId}")
    public ApplicantResponse getApplicantDetail(@PathVariable Long applicantId) {
        return applicantService.getApplicantDetail(applicantId);
    }
}
