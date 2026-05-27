package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cotato.backend.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // JSON 파싱/enum 매핑 실패 → 400
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadJson(
            org.springframework.http.converter.HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        log.error("JSON 파싱 실패: {}", e.getMostSpecificCause() != null ? e.getMostSpecificCause().getMessage() : e.getMessage());
        log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(ErrorCode.BAD_REQUEST, request)); // ⚠ ErrorCode에 BAD_REQUEST가 없다면 적절한 코드로 교체
    }

    // @Valid 검증 실패 → 400
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            org.springframework.web.bind.MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        var msgs = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();
        log.error("검증 실패: {}", msgs);
        log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(ErrorCode.BAD_REQUEST, request)); // ⚠ 필요 시 별도 ErrorCode 추가
    }

    // 서비스 레이어에서 던진 상태 예외 존중(404 등)
    @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(
            org.springframework.web.server.ResponseStatusException e,
            HttpServletRequest request
    ) {
        log.error("ResponseStatusException: [{}] {}", e.getStatusCode(), e.getReason());
        log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());

        // 상태코드에 맞춰 ErrorCode 매핑 (프로젝트의 ErrorCode에 맞게 조정)
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());
        ErrorCode code = switch (status) {
            case NOT_FOUND -> ErrorCode.NOT_FOUND;            // ⚠ 없으면 추가/교체
            case BAD_REQUEST -> ErrorCode.BAD_REQUEST;
            default -> ErrorCode.INTERNAL_SERVER_ERROR;
        };
        return ResponseEntity.status(status).body(ErrorResponse.of(code, request));
    }

    // (선택) 핸들러 없음 → 404
    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandler(
            org.springframework.web.servlet.NoHandlerFoundException e,
            HttpServletRequest request
    ) {
        log.error("핸들러 없음: {} {}", e.getHttpMethod(), e.getRequestURL());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(ErrorCode.NOT_FOUND, request));
    }

    // 처리되지 않은 모든 예외를 잡는 핸들러
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception e, HttpServletRequest request) {
		log.error("처리되지 않은 예외 발생: ", e);
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
		ErrorResponse errorResponse = ErrorResponse.of(
			ErrorCode.INTERNAL_SERVER_ERROR,
			request
		);
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(errorResponse);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppCustomException(AppException e, HttpServletRequest request) {
		log.error("AppException 발생: {}", e.getErrorCode().getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
		return ResponseEntity
			.status(e.getErrorCode().getHttpStatus())
			.body(errorResponse);
	}
}