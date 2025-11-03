package lotto.dto;

import java.util.Set;

public record WinningCondition(
        Set<Integer> winningNumbers, // 당첨 번호
        int bonusNumber // 보너스 번호
) {
    public static WinningCondition of(Set<Integer> winningNumbers, int bonusNumber) {
        return new WinningCondition(winningNumbers, bonusNumber);
    }
}
