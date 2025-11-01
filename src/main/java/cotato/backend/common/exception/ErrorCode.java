package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// 공통 에러
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON-001"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못되었습니다.", "COMMON-002"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다.", "COMMON-003"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-004"),

	// 지원자 관련 에러
	APPLICANT_NOT_FOUND(HttpStatus.NOT_FOUND, "지원자를 찾을 수 없습니다.", "APPLICANT-001"),

	// 지원 서류 관련 에러
	APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "지원 서류를 찾을 수 없습니다.", "APPLICATION-001"),

	// 운영진 관련 에러
	STAFF_NOT_FOUND(HttpStatus.NOT_FOUND, "운영진을 찾을 수 없습니다.", "STAFF-001"),

	// 좋아요 관련 에러
	DUPLICATE_LIKE(HttpStatus.CONFLICT, "이미 좋아요를 누른 서류입니다.", "LIKE-001"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다.", "LIKE-002"),

	// Validation 에러
	INVALID_NAME_LENGTH(HttpStatus.BAD_REQUEST, "이름은 2~10글자여야 합니다.", "VALIDATION-001"),
	INVALID_AGE_RANGE(HttpStatus.BAD_REQUEST, "나이는 22~30살이어야 합니다.", "VALIDATION-002"),
	INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "휴대폰 번호는 010으로 시작하는 11자리여야 합니다.", "VALIDATION-003"),
	INVALID_PART(HttpStatus.BAD_REQUEST, "파트는 기획/디자이너/프론트엔드/백엔드 중 하나여야 합니다.", "VALIDATION-004"),
	INVALID_SCORE_RANGE(HttpStatus.BAD_REQUEST, "실력/열정은 0~10 사이여야 합니다.", "VALIDATION-005"),
	INVALID_PERIOD(HttpStatus.BAD_REQUEST, "지원 기수는 1 이상이어야 합니다.", "VALIDATION-006"),
	;

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}