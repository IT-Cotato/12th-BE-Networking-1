package cotato.backend.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import cotato.backend.api.dto.applicant.ApplicantRequest;
import cotato.backend.api.dto.applicant.ApplicantResponse;
import cotato.backend.domain.service.ApplicantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/{id}")
    public ApplicantResponse get(@PathVariable Long id) {
        var a = applicantService.getById(id);
        return new ApplicantResponse(a.getId(), a.getName(), a.getAge(), a.getPhoneNumber());
    }

    @PutMapping("/{id}")
    public ApplicantResponse update(@PathVariable Long id, @RequestBody @Valid ApplicantRequest req) {
        var a = applicantService.update(id, req.name(), req.age(), req.phoneNumber());
        return new ApplicantResponse(a.getId(), a.getName(), a.getAge(), a.getPhoneNumber());
    }

    // (옵션) 직접 등록
    @PostMapping
    public ApplicantResponse create(@RequestBody @Valid ApplicantRequest req) {
        var a = applicantService.upsertByPhone(req.name(), req.age(), req.phoneNumber());
        return new ApplicantResponse(a.getId(), a.getName(), a.getAge(), a.getPhoneNumber());
    }
}
