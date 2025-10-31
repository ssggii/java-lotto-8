package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.global.exception.ErrorCode.*;

public class Lotto {

    private static final int NUMBERS_SIZE = 6;
    private static final int NUMBER_RANGE_MIN = 1;
    private static final int NUMBER_RANGE_MAX = 45;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberRange(numbers);
        validateUniqueNumbers(numbers);
    }

    private void validateUniqueNumbers(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(NOT_UNIQUE_NUMBERS.getMessage());
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        boolean hasInvalidRange = numbers.stream().anyMatch(number -> number < NUMBER_RANGE_MIN || number > NUMBER_RANGE_MAX);
        if (hasInvalidRange) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALID_NUMBERS_SIZE.getMessage());
        }
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

}
