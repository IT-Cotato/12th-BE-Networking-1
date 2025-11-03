package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public DataResponse<DefaultIdResponse> save(@RequestBody ApplicantRequest request) {
        Long id = applicantService.save(request);
        return DataResponse.created(DefaultIdResponse.of(id));
    }
}
