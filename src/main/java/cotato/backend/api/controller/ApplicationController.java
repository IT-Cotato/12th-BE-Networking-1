package cotato.backend.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cotato.backend.api.dto.response.ApplicationListResponseDTO;
import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.api.dto.request.SubmitApplicationDTO;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.recruitment.application.ApplicationService;
import cotato.backend.domain.recruitment.dto.response.ApplicationDetailResponseDTO;
import cotato.backend.domain.recruitment.entity.enums.FilterBy;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applications")
public class ApplicationController {
	private final ApplicationService applicationService;

	@Operation(summary = "지원 서류 제출 API")
	@PostMapping("/submit")
	public ResponseEntity<DataResponse<DefaultIdResponse>> submitApplication(@RequestBody SubmitApplicationDTO submitApplicationDTO) {
		return ResponseEntity.ok(
			DataResponse.created(
				DefaultIdResponse.of(applicationService.submitApplication(submitApplicationDTO))
			)
		);
	}

	@Operation(summary = "지원 서류 세부 조회 API")
	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ApplicationDetailResponseDTO>> getApplication(Long applicationId) {
		return ResponseEntity.ok(
			DataResponse.from(applicationService.getApplication(applicationId))
		);
	}

	@Operation(summary = "서류 리스트 조회 API")
	@GetMapping
	public ResponseEntity<DataResponse<ApplicationListResponseDTO>> getApplicationList(
		// 필터링 기준 period, likes, both
		// 필터링 기준 1,2,3 모두 pageSize는 고정이므로 따로 입력받지 않았음
		@RequestParam(defaultValue = "period") FilterBy filterBy,
		// 만약 period나 both를 기준으로 필터링할 경우, period 값을 입력받음
		@RequestParam(required = false) Integer period,
		@RequestParam(defaultValue = "1") Integer page
	) {
		if ((filterBy == FilterBy.period || filterBy == FilterBy.both) && period == null) {
			// period와 both는 특정 기수를 조회하는 필터링이므로 period값이 반드시 있어야 함
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "period 또는 both 조회는 period를 필수로 입력해야 합니다"
			);
		}

		return ResponseEntity.ok(
			// 실제 페이징은 0부터 시작하므로 page-1로 넣음
			DataResponse.from(applicationService.getApplicationList(filterBy, period, page -1 ))
		);
	}
}