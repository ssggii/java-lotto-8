package lotto.model;

import java.util.List;

import static lotto.global.exception.ErrorCode.INVALID_NUMBERS_SIZE;

public class Lotto {

    private static final int NUMBERS_SIZE = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
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
