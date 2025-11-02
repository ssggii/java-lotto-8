package lotto.global.util;

import lotto.global.exception.UserInputException;

import static lotto.global.exception.ErrorCode.*;
import static lotto.model.Lotto.LOTTO_PRICE_UNIT;

public class UserInputParser {

    public static int parsePurchaseAmount(String purchaseAmountInput) {
        try {
            int purchaseAmount = Integer.parseInt(purchaseAmountInput);
            validate(purchaseAmount);
            return purchaseAmount;
        } catch (NumberFormatException e) {
            throw new UserInputException(NOT_NUMBER_FORMAT);
        }
    }

    private static void validate(int purchaseAmount) {
        validateNegativeNumber(purchaseAmount);
        validateNotDividedUp(purchaseAmount);
    }

    private static void validateNegativeNumber(int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new UserInputException(NEGATIVE_INTEGER);
        }
    }

    private static void validateNotDividedUp(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE_UNIT != 0) {
            throw new UserInputException(NOT_DIVIDED_UP);
        }
    }

}
