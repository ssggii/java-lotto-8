package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCountResult;
import lotto.global.exception.UserInputException;

import java.util.List;
import java.util.Map;

import static lotto.global.exception.ErrorCode.NEGATIVE_DIGIT;

public class WinningResultProcessor {

    private static final int PERCENTAGE = 100;

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

    public double calculateReturnRate(int purchaseAmount, int totalPrizeMoney) {
        validateNegativePurchaseAmount(purchaseAmount);
        return (double) totalPrizeMoney / purchaseAmount * PERCENTAGE;
    }

    private void validateNegativePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new UserInputException(NEGATIVE_DIGIT);
        }
    }

}
