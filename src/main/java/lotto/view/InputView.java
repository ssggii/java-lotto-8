package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.global.util.StringValidator;

import static lotto.global.ViewMessage.*;

public class InputView {

    public String getPurchaseAmountInput() {
        System.out.println(PURCHASE_INPUT_INFO.getMessage());
        return getTrimmed(Console.readLine());
    }

    public String getWinningNumberInput() {
        System.out.println(WINNING_NUMBERS_INPUT_INFO.getMessage());
        return getTrimmed(Console.readLine());
    }

    public String getBonusNumberInput() {
        System.out.println(BONUS_NUMBER_INPUT_INFO.getMessage());
        return getTrimmed(Console.readLine());
    }

    private String getTrimmed(String input) {
        String trimmedInput = input.trim();
        StringValidator.validateBlankValue(trimmedInput);
        return trimmedInput;
    }

}
