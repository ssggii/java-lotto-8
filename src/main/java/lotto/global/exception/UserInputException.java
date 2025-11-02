package lotto.global.exception;

public class UserInputException extends IllegalArgumentException {

    private final ErrorCode errorCode;

    public UserInputException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
