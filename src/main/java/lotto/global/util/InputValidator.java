package lotto.global.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static lotto.global.exception.ErrorCode.*;

public class InputValidator {

    public static void validateNegativeNumber(int inputNum) {
        if (inputNum < 0) {
            throw new IllegalArgumentException(NEGATIVE_DIGIT.getMessage());
        }
    }

    public static void validateNotDividedUp(int dividend, int divisor) {
        if (dividend % divisor != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_NOT_DIVIDED_UP.getMessage());
        }
    }

    public static void validateNumbersCount(Collection<?> toValidate, int expectedCount) {
        if (toValidate.size() != expectedCount) {
            throw new IllegalArgumentException(INVALID_NUMBERS_SIZE.getMessage());
        }
    }

    public static void validateUniqueNumbers(List<Integer> originalNumbers) {
        List<Integer> distinctNumbers = originalNumbers.stream().distinct().toList();
        if (distinctNumbers.size() != originalNumbers.size()) {
            throw new IllegalArgumentException(NOT_UNIQUE_NUMBERS.getMessage());
        }
    }

    public static void validateSetHasNegativeNumber(Set<Integer> targetNumbers) {
        boolean hasNegativeNumber = targetNumbers.stream().anyMatch(number -> number < 0);
        if (hasNegativeNumber) {
            throw new IllegalArgumentException(NEGATIVE_DIGIT.getMessage());
        }
    }

    public static void validateNumberRange(int targetNumber, int min, int max) {
        if (targetNumber < min || targetNumber > max) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }

    public static void validateNumbersRange(Collection<Integer> numbers, int min, int max) {
        boolean isOutOfRange = numbers.stream().anyMatch(number -> number < min || number > max);
        if (isOutOfRange) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }
}
