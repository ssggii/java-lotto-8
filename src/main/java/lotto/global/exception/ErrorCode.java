package lotto.global.exception;

public enum ErrorCode {

    INVALID_NUMBERS_SIZE("[ERROR] 로또 번호는 6개여야 합니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
