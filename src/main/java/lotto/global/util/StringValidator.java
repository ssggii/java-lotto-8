package lotto.global.util;

import static lotto.global.exception.ErrorCode.BLANK_VALUE;

public class StringValidator {

    public static void validateBlankValue(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(BLANK_VALUE.getMessage());
        }
    }

}
