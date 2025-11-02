package lotto.dto;

import lotto.model.Ranking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public record WinningCountResult(
        Map<Ranking, Integer> winningCountMap
) {
    public static WinningCountResult create() {
        HashMap<Ranking, Integer> winningCountMap = new HashMap<>();
        Arrays.stream(Ranking.values()).forEach(ranking -> winningCountMap.put(ranking, 0));
        return new WinningCountResult(winningCountMap);
    }
}
