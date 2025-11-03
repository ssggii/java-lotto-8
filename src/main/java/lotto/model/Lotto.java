package lotto.model;

import lotto.dto.WinningCondition;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.global.util.InputValidator.*;

public class Lotto {

    public static final int LOTTO_NUMBERS_SIZE = 6;
    public static final int NUMBER_RANGE_MIN = 1;
    public static final int NUMBER_RANGE_MAX = 45;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumbersCount(numbers, LOTTO_NUMBERS_SIZE);
        validateUniqueNumbers(numbers);
        validateNumbersRange(numbers, NUMBER_RANGE_MIN, NUMBER_RANGE_MAX);
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
