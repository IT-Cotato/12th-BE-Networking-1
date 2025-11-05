package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.application.ApplicantService;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantResponse;
import cotato.backend.domain.example.dto.response.ApplicationFormResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public DataResponse<DefaultIdResponse> createApplicant(@Valid @RequestBody ApplicantRequest req) {
        Long id = applicantService.create(req);
        return DataResponse.created(DefaultIdResponse.of(id));
    }

    @GetMapping("/{id}")
    public DataResponse<ApplicantResponse> getApplicant(@PathVariable Long id) {
        ApplicantResponse res = applicantService.getById(id);
        return DataResponse.from(res);
    }

    @PutMapping("/{id}")
    public DataResponse<ApplicantResponse> updateApplicant(
            @PathVariable Long id,
            @Valid @RequestBody ApplicantRequest req) {
        ApplicantResponse res = applicantService.update(id,req);
        return DataResponse.from(res);
    }

}
