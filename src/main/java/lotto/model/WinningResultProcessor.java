package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCountResult;

import java.util.List;
import java.util.Map;

public class WinningResultProcessor {

    public WinningCountResult calculateWinningCount(List<DrawResult> drawResults) {
        WinningCountResult winningCountResult = WinningCountResult.create();
        Map<Ranking, Integer> winningCounts = winningCountResult.winningCountMap();

        for (DrawResult drawResult : drawResults) {
            Ranking ranking = drawResult.ranking();
            int currentCount = winningCounts.getOrDefault(ranking, 0);
            winningCounts.put(ranking, currentCount + 1);
        }

        return winningCountResult;
    }

}
