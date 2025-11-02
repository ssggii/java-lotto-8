package lotto.global.util;

import lotto.global.exception.UserInputException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.global.exception.ErrorCode.*;
import static lotto.model.Lotto.*;

public class UserInputParser {

    private static final String DIGIT_DELIMITER = ",";

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

    private static void validateNegativeNumber(int inputNum) {
        if (inputNum < 0) {
            throw new UserInputException(NEGATIVE_DIGIT);
        }
    }

    private static void validateNotDividedUp(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE_UNIT != 0) {
            throw new UserInputException(PURCHASE_AMOUNT_NOT_DIVIDED_UP);
        }
    }

    public static Set<Integer> parseWinningNumber(String winningNumberInput) {
        try {
            List<String> winningNumberTokens = Arrays.stream(winningNumberInput.split(DIGIT_DELIMITER)).map(String::trim).toList();
            Set<Integer> winningNumbers = winningNumberTokens.stream().map(Integer::parseInt).collect(Collectors.toSet());
            validate(winningNumbers);
            return winningNumbers;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new UserInputException(NOT_NUMBER_FORMAT);
        }
    }

    private static void validate(Set<Integer> winningNumbers) {
        validateNumbersCount(winningNumbers);
        validateNegativeNumber(winningNumbers);
        validateAllNumberRange(winningNumbers);
    }

    private static void validateAllNumberRange(Set<Integer> winningNumbers) {
        boolean isOutOfRange = winningNumbers.stream().anyMatch(number -> number < NUMBER_RANGE_MIN || number > NUMBER_RANGE_MAX);
        if (isOutOfRange) {
            throw new UserInputException(INVALID_NUMBER_RANGE);
        }
    }

    private static void validateNegativeNumber(Set<Integer> winningNumbers) {
        boolean hasNegativeNumber = winningNumbers.stream().anyMatch(number -> number < 0);
        if (hasNegativeNumber) {
            throw new UserInputException(NEGATIVE_DIGIT);
        }
    }

    private static void validateNumbersCount(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != NUMBERS_SIZE) {
            throw new UserInputException(INVALID_NUMBERS_SIZE);
        }
    }

    public static int parseBonusNumber(String bonusNumberInput) {
        int bonusNumber = Integer.parseInt(bonusNumberInput);
        validateNegativeNumber(bonusNumber);
        validateNumberRange(bonusNumber);
        return bonusNumber;
    }

    private static void validateNumberRange(int bonusNumber) {
        if (bonusNumber < NUMBER_RANGE_MIN || bonusNumber > NUMBER_RANGE_MAX) {
            throw new UserInputException(INVALID_NUMBER_RANGE);
        }
    }

}
