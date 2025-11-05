package cotato.backend.api.controller;

import cotato.backend.domain.example.application.ApplicantService;
import cotato.backend.domain.example.dto.request.UpdateApplicantRequest;
import cotato.backend.domain.example.entity.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    // 지원자 단건 조회 반환
    @GetMapping("/{id}")
    public ResponseEntity<Applicant> get(@PathVariable Long id) {
        return ResponseEntity.ok(applicantService.getById(id));
    }

    // 제공된 필드만 부분 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody UpdateApplicantRequest request
    ) {
        applicantService.update(id, request.getName(), request.getAge(), request.getPhoneNumber());
        return ResponseEntity.noContent().build();
    }
}


