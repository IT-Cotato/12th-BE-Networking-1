package cotato.backend.applicant.controller;

import cotato.backend.applicant.dto.ApplicantCreateResponseDto;
import cotato.backend.applicant.dto.ApplicantUpdateRequestDto;
import cotato.backend.applicant.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    /**
     * 지원자 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantCreateResponseDto> getApplicant(@PathVariable Long id) {
        ApplicantCreateResponseDto response = applicantService.getApplicant(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 전체 지원자 조회
     */
    @GetMapping
    public ResponseEntity<List<ApplicantCreateResponseDto>> getAllApplicants() {
        List<ApplicantCreateResponseDto> list = applicantService.getAllApplicants();
        return ResponseEntity.ok(list);
    }

    /**
     * 지원자 정보 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateApplicant(
            @PathVariable Long id,
            @RequestBody ApplicantUpdateRequestDto request) {

        applicantService.updateApplicant(id, request);
        return ResponseEntity.ok("지원자 정보가 수정되었습니다.");
    }

    /**
     * 지원자 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplicant(@PathVariable Long id) {
        applicantService.deleteApplicant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("지원자 정보가 삭제되었습니다.");
    }
}
