package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.global.util.StringValidator;

import static lotto.global.ViewMessage.PURCHASE_INPUT_INFO;

public class InputView {

    public String getPurchaseAmountInput() {
        System.out.println(PURCHASE_INPUT_INFO.getMessage());
        String trimmedInput = Console.readLine().trim();
        StringValidator.validateBlankValue(trimmedInput);
        return trimmedInput;
    }

}
