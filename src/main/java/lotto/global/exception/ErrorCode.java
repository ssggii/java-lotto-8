package lotto.global.exception;

public enum ErrorCode {

    INVALID_NUMBERS_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    INVALID_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    NOT_UNIQUE_NUMBERS("[ERROR] 로또 번호는 중복될 수 없습니다."),
    BLANK_VALUE("[ERROR] 입력값이 빈 문자열이거나 공백입니다."),
    PURCHASE_AMOUNT_NOT_NUMBER_FORMAT("[ERROR] 구입 금액을 숫자로 변환할 수 없습니다."),
    NEGATIVE_PURCHASE_AMOUNT("[ERROR] 구입 금액으로 음수는 불가능합니다."),
    PURCHASE_AMOUNT_NOT_DIVIDED_UP("[ERROR] 구입 금액은 1000원 단위로 나누어 떨어져야 합니다."),
    INVALID_PURCHASE_AMOUNT("[ERROR] 구입 금액을 1000원 단위로 입력해주세요.");


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
