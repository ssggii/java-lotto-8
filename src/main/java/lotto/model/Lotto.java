package lotto.model;

import lotto.dto.WinningCondition;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.global.exception.ErrorCode.*;

public class Lotto {

    public static final int NUMBERS_SIZE = 6;
    public static final int NUMBER_RANGE_MIN = 1;
    public static final int NUMBER_RANGE_MAX = 45;
    public static final int LOTTO_PRICE_UNIT = 1000;

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
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();
        return new Lotto(sortedNumbers);
    }

    public Integer findHittingNumberCount(Set<Integer> targetNumbers) {
        Set<Integer> lottoNumbers = new HashSet<>(numbers);
        Set<Integer> hittingNumbers = lottoNumbers.stream()
                .filter(targetNumbers::contains)
                .collect(Collectors.toSet());
        return hittingNumbers.size();
    }

    public boolean isHitBonusNumber(WinningCondition winningCondition) {
        int bonusNumber = winningCondition.bonusNumber();
        Set<Integer> notHitNumbers = numbers.stream()
                .filter(number -> !winningCondition.winningNumbers().contains(number))
                .collect(Collectors.toSet());
        return notHitNumbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

}
