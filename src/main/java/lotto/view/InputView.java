package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.global.util.StringValidator;

import static lotto.global.ViewMessage.PURCHASE_INPUT_INFO;
import static lotto.global.ViewMessage.WINNING_NUMBERS_INPUT_INFO;

public class InputView {

    public String getPurchaseAmountInput() {
        System.out.println(PURCHASE_INPUT_INFO.getMessage());
        return getTrimmed(Console.readLine());
    }

    public String getWinningNumberInput() {
        System.out.println(WINNING_NUMBERS_INPUT_INFO.getMessage());
        return getTrimmed(Console.readLine());
    }

    private String getTrimmed(String input) {
        String trimmedInput = input.trim();
        StringValidator.validateBlankValue(trimmedInput);
        return trimmedInput;
    }

}
