package lotto.global.exception;

public enum ErrorCode {

    INVALID_NUMBERS_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    INVALID_NUMBER_RANGE("[ERROR] 로또 번호의 범위가 올바르지 않습니다."),
    NOT_UNIQUE_NUMBERS("[ERROR] 로또 번호는 중복될 수 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }

}
