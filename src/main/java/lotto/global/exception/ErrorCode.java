package lotto.global.exception;

public enum ErrorCode {

    INVALID_NUMBERS_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    INVALID_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    NOT_UNIQUE_NUMBERS("[ERROR] 로또 번호는 중복될 수 없습니다."),
    NEGATIVE_PURCHASE_AMOUNT("[ERROR] 구입 금액은 음수일 수 없습니다."),
    BLANK_VALUE("[ERROR] 입력값이 빈 문자열이거나 공백입니다."),
    NOT_INTEGER_FORMAT("[ERROR] 정수로 변환할 수 없습니다 : %s"),
    NOT_POSITIVE_INTEGER("[ERROR] 변환값이 음수입니다 : %s");

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
