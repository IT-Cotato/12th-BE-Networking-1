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

	// Applicant 관련
	INVALID_NAME_LENGTH(HttpStatus.BAD_REQUEST, "이름은 2~10글자여야 합니다.", "APPLICANT-001"),
	INVALID_AGE_RANGE(HttpStatus.BAD_REQUEST, "나이는 22~30살이어야 합니다.", "APPLICANT-002"),
	INVALID_GENERATION(HttpStatus.BAD_REQUEST, "기수는 1기 이상이어야 합니다.", "APPLICANT-003"),

	// ApplicationForm 관련
	APPLICATION_FORM_NOT_FOUND(HttpStatus.NOT_FOUND, "지원서를 찾을 수 없습니다.", "APPLICATIONFORM-001"),

	// Member 관련
	MEMBER_NAME_REQUIRED(HttpStatus.BAD_REQUEST, "이름은 필수입니다.", "MEMBER-001"),
	MEMBER_GENERATION_REQUIRED(HttpStatus.BAD_REQUEST, "기수는 필수입니다.", "MEMBER-002"),
	MEMBER_AGE_REQUIRED(HttpStatus.BAD_REQUEST, "나이는 필수입니다.", "MEMBER-003"),
	MEMBER_PART_REQUIRED(HttpStatus.BAD_REQUEST, "파트는 필수입니다.", "MEMBER-004"),
	MEMBER_PHONE_NUM_REQUIRED(HttpStatus.BAD_REQUEST, "전화번호는 필수입니다.", "MEMBER-005"),

	MEMBER_INVALID_NAME_LENGTH(HttpStatus.BAD_REQUEST, "이름은 2~10글자여야 합니다.", "MEMBER-006"),
	MEMBER_INVALID_GENERATION(HttpStatus.BAD_REQUEST, "기수는 1기 이상이어야 합니다.", "MEMBER-007"),
	MEMBER_INVALID_AGE_RANGE(HttpStatus.BAD_REQUEST, "나이는 22~30살이어야 합니다.", "MEMBER-008"),
	MEMBER_INVALID_PHONE_NUM(HttpStatus.BAD_REQUEST, "전화번호는 11자리여야 합니다.", "MEMBER-009"),

	MEMBER_DUPLICATE_PHONE_NUM(HttpStatus.BAD_REQUEST, "이미 등록된 전화번호입니다.", "MEMBER-010"),

	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다.", "MEMBER-011"),
	MEMBER_ROLE_REQUIRED(HttpStatus.BAD_REQUEST, "역할은 필수입니다.", "MEMBER-012"),

	// ApplicationFormLike 관련
	LIKE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 좋아요를 눌렀습니다.", "APPLICATIONFORMLIKE-001"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다.", "APPLICATIONFORMLIKE-002"),
	LIKE_ONLY_FOR_STAFF(HttpStatus.FORBIDDEN, "운영진만 좋아요를 누를 수 있습니다.", "APPLICATIONFORMLIKE-003"),

	//500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-004");

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}