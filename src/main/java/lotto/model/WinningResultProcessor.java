package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCountResult;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static lotto.global.util.InputValidator.validateNegativeNumber;

public class WinningResultProcessor {

    private static final int PERCENTAGE = 100;

    public WinningCountResult calculateWinningCount(List<DrawResult> drawResults) {
        WinningCountResult winningCountResult = WinningCountResult.create();
        Map<Ranking, Integer> winningCounts = winningCountResult.winningCountMap();

        drawResults.stream()
                .filter(drawResult -> drawResult.ranking() != null)
                .forEach(drawResult -> {
                    Ranking ranking = drawResult.ranking();
                    int currentCount = winningCounts.getOrDefault(ranking, 0);
                    winningCounts.put(ranking, currentCount + 1);
                });

        return winningCountResult;
    }

    public double calculateReturnRate(int purchaseAmount, WinningCountResult winningCountResult) {
        validateNegativeNumber(purchaseAmount);
        BigInteger totalPrizeMoney = calculateTotalPrizeMoney(winningCountResult);
        return totalPrizeMoney.doubleValue() / (double) purchaseAmount * PERCENTAGE;
    }

    private BigInteger calculateTotalPrizeMoney(WinningCountResult winningCountResult) {
        Map<Ranking, Integer> winningCounts = winningCountResult.winningCountMap();

        return winningCounts.keySet().stream()
                .map(ranking -> {
                    long prize = ranking.getPrize();
                    long winningCount = winningCounts.get(ranking);
                    return BigInteger.valueOf(prize * winningCount);
                })
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

}
