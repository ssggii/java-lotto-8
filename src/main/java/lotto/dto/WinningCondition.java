package lotto.dto;

import java.util.Set;

public record WinningCondition(
        Set<Integer> winningNumbers,
        int bonusNumber
) {
    public static WinningCondition of(Set<Integer> winningNumbers, int bonusNumber) {
        return new WinningCondition(winningNumbers, bonusNumber);
    }
}
