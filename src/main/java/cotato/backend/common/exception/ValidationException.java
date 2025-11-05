package cotato.backend.common.exception;

public class ValidationException extends AppException {
    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
