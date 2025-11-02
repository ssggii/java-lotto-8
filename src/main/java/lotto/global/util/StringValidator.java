package lotto.global.util;

import lotto.global.exception.UserInputException;

import static lotto.global.exception.ErrorCode.BLANK_VALUE;

public class StringValidator {

    public static void validateBlankValue(String input) {
        if (input == null || input.isBlank()) {
            throw new UserInputException(BLANK_VALUE);
        }
    }

}
