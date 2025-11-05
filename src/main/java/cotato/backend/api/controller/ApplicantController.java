package cotato.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.recruitment.application.ApplicantService;
import cotato.backend.domain.recruitment.dto.response.ApplicantDetailResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applicants")
public class ApplicantController {
	private final ApplicantService applicantService;

	@Operation(summary = "지원자 정보 조회 API")
	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ApplicantDetailResponseDTO>> getApplicant(Long applicantId) {
		return ResponseEntity.ok(
			DataResponse.from(applicantService.getApplicant(applicantId))
		);
	}
}
