package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	//400
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON-001"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못되었습니다.", "COMMON-002"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다.", "COMMON-003"),

	//500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-004"),

    // 지원자 에러코드
    APPLICANT_NOT_FOUND(HttpStatus.NOT_FOUND, "지원자를 찾을 수 없습니다.", "APP-001"),

    // 지원서 에러코드
    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "지원서를 찾을 수 없습니다.", "APL-001"),
    APPLICATION_ALREADY_EXISTS(HttpStatus.NOT_FOUND, "이미 지원하였습니다.", "APL-002"),

    // 운영진 에러코드
    MANAGER_NOT_FOUND(HttpStatus.NOT_FOUND, "운영진을 찾을 수 없습니다", "M-001");

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}