package lotto.dto;

import java.util.Set;

public record AllWinningNumbers(
        Set<Integer> winningNumbers,
        int bonusNumber
) {
    public static AllWinningNumbers of(Set<Integer> winningNumbers, int bonusNumber) {
        return new AllWinningNumbers(winningNumbers, bonusNumber);
    }
}
