package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCountResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningResultProcessor {

    public WinningCountResult calculateWinningCount(List<DrawResult> drawResults) {
        Map<Ranking, Integer> winningCounts = new HashMap<>();

        drawResults.forEach(drawResult -> {
            Ranking ranking = drawResult.ranking();
            int currentCount = winningCounts.getOrDefault(ranking, 0);
            winningCounts.put(ranking, currentCount + 1);
        });

        return WinningCountResult.from(winningCounts);
    }

}
