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
    INVALID_FILTER(HttpStatus.BAD_REQUEST, "filterBy 파라미터 오류 (likes, period, periodlikes 중 하나여야 합니다.)", "APP-003"),
    INVALID_PAGE(HttpStatus.BAD_REQUEST, "페이지 번호는 1 이상이어야 합니다.", "APP-004"),

    //404
    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 지원서를 찾을 수 없습니다.", "APP-001"),
    APPLICATION_LIST_EMPTY(HttpStatus.NOT_FOUND, "검색 결과가 없습니다.", "APP-002"),
    APPLICANT_NOT_FOUND(HttpStatus.NOT_FOUND, "지원자를 찾을 수 없습니다.", "APPLICANT-001"),

    //409
    DUPLICATE_PHONE_NUMBER(HttpStatus.CONFLICT, "이미 등록된 전화번호입니다.", "COMMON-005"),

    //500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-004"),
	;

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}