package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// 400
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON-001"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못되었습니다.", "COMMON-002"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다.", "COMMON-003"),

    // 지원서
    INVALID_FILTER_BY(HttpStatus.BAD_REQUEST, "잘못된 filterBy 값입니다.", "APP-001"),
    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 지원서를 찾을 수 없습니다.", "APP-002"),
    ALREADY_LIKED_APPLICATION(HttpStatus.CONFLICT, "이미 좋아요를 누른 지원서입니다.", "APP-003"),

    // 지원자
    APPLICANT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 지원자를 찾을 수 없습니다.", "APL-001"),

    // 운영진
    STAFF_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 운영진을 찾을 수 없습니다.", "STAFF-001"),
    INVALID_STAFF_ROLE(HttpStatus.BAD_REQUEST, "존재하지 않는 운영진 역할입니다.", "STAFF-002"),

    // 500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-004");

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}