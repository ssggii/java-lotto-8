package lotto.global.util;

import lotto.global.exception.UserInputException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.global.exception.ErrorCode.*;
import static lotto.global.util.InputValidator.*;
import static lotto.model.Lotto.*;
import static lotto.model.LottoIssuer.DEFAULT_LOTTO_PRICE;

public class UserInputParser {

    private static final String DIGIT_DELIMITER = ",";

    public static int parsePurchaseAmount(String purchaseAmountInput) {
        try {
            int purchaseAmount = Integer.parseInt(purchaseAmountInput);
            validate(purchaseAmount);
            return purchaseAmount;
        } catch (NumberFormatException e) {
            throw new UserInputException(NOT_NUMBER_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new UserInputException(errorCodeByMessage(e.getMessage()));
        } catch (Exception e) {
            throw new UserInputException(INTERNAL_SERVER_ERROR);
        }
    }

    private static void validate(int purchaseAmount) {
        validateNegativeNumber(purchaseAmount);
        validateNotDividedUp(purchaseAmount, DEFAULT_LOTTO_PRICE);
    }


    public static Set<Integer> parseWinningNumber(String winningNumberInput) {
        try {
            List<String> winningNumberTokens = getWinningNumberTokens(winningNumberInput);
            List<Integer> originalWinningNumbers = parseValidNumbers(winningNumberTokens);
            Set<Integer> winningNumbers = new HashSet<>(originalWinningNumbers);
            validateWinningNumbers(winningNumbers, originalWinningNumbers);
            return winningNumbers;
        } catch (IllegalArgumentException e) {
            throw new UserInputException(errorCodeByMessage(e.getMessage()));
        } catch (Exception e) {
            throw new UserInputException(INTERNAL_SERVER_ERROR);
        }
    }

    private static List<String> getWinningNumberTokens(String winningNumberInput) {
        List<String> winningNumberTokens = Arrays.stream(winningNumberInput.split(DIGIT_DELIMITER))
                .map(String::trim)
                .toList();
        validateNumbersCount(winningNumberTokens, LOTTO_NUMBERS_SIZE);
        return winningNumberTokens;
    }

    private static List<Integer> parseValidNumbers(List<String> numberTokens) {
        try {
            return numberTokens.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_FORMAT.getMessage());
        }
    }

    private static void validateWinningNumbers(Set<Integer> winningNumbers, List<Integer> originalWinningNumbers) {
        validateUniqueNumbers(originalWinningNumbers);
        validateValidRange(winningNumbers);
    }

    private static void validateValidRange(Set<Integer> winningNumbers) {
        validateSetHasNegativeNumber(winningNumbers);
        validateNumbersRange(winningNumbers, NUMBER_RANGE_MIN, NUMBER_RANGE_MAX);
    }

    public static int parseBonusNumber(String bonusNumberInput, Set<Integer> winningNumbers) {
        try {
            int bonusNumber = Integer.parseInt(bonusNumberInput);
            validateNegativeNumber(bonusNumber);
            validateNumberRange(bonusNumber, NUMBER_RANGE_MIN, NUMBER_RANGE_MAX);
            validateUniqueBonusNumber(winningNumbers, bonusNumber);
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new UserInputException(NOT_NUMBER_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new UserInputException(errorCodeByMessage(e.getMessage()));
        } catch (Exception e) {
            throw new UserInputException(INTERNAL_SERVER_ERROR);
        }
    }

    private static void validateUniqueBonusNumber(Set<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(NOT_UNIQUE_NUMBERS.getMessage());
        }
    }

}
