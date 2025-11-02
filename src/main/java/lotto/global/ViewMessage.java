package lotto.global;

public enum ViewMessage {

    PURCHASE_INPUT_INFO("구입금액을 입력해 주세요."),
    LOTTO_COUNT_INFO("%d개를 구매했습니다."),
    WINNING_NUMBERS_INPUT_INFO("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_INPUT_INFO("보너스 번호를 입력해 주세요.");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }

}
