package cotato.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.recruitment.application.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/likes")
public class LikeController {
	private final LikeService likeService;

	@Operation(summary = "서류 좋아요 API")
	@PostMapping
	public ResponseEntity<DataResponse<DefaultIdResponse>> likeApplication(Long applicationId, Long managementId) {
		// 실제 구현에서는 로그인된 정보를 조회해서 운영진 정보를 가져오겠지만,
		// 로그인과 관련된 부분은 과제 요구사항에 포함되어 있지 않으므로 managementId 값을 입력받아 처리하는 것으로 임의구현하였음
		return ResponseEntity.ok(
			DataResponse.created(
				DefaultIdResponse.of(likeService.likeApplication(applicationId, managementId))
			)
		);
	}
}