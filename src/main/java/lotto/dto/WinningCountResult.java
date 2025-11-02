package lotto.dto;

import lotto.model.Ranking;

import java.util.Map;

public record WinningCountResult(
        Map<Ranking, Integer> winningCountForRanking
) {
    public static WinningCountResult from(Map<Ranking, Integer> winningCountForRanking) {
        return new WinningCountResult(winningCountForRanking);
    }
}
