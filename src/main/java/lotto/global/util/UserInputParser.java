package lotto.global.util;

import static lotto.global.exception.ErrorCode.NOT_INTEGER_FORMAT;
import static lotto.global.exception.ErrorCode.NOT_POSITIVE_INTEGER;

public class UserInputParser {

    public static int parsePositiveInteger(String input) {
        try {
            int parsedInt = Integer.parseInt(input);
            validatePositiveInteger(parsedInt);
            return parsedInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_FORMAT.getMessage(input));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NOT_POSITIVE_INTEGER.getMessage(input));
        }
    }

    private static void validatePositiveInteger(int parsedInt) {
        if (parsedInt < 0) {
            throw new IllegalArgumentException();
        }
    }
}
