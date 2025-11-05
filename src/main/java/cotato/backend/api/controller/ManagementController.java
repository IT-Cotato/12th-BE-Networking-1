package cotato.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.recruitment.application.ManagementService;
import cotato.backend.domain.recruitment.dto.response.ManagementDetailResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/managements")
public class ManagementController {
	private final ManagementService managementService;

	@Operation(summary = "운영진 정보 조회 API")
	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ManagementDetailResponseDTO>> getManagement(Long managementId) {
		return ResponseEntity.ok(
			DataResponse.from(managementService.getManagement(managementId))
		);
	}
}