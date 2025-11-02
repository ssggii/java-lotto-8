package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCountResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static lotto.global.exception.ErrorCode.NEGATIVE_DIGIT;
import static lotto.model.Ranking.FIRST;
import static lotto.model.Ranking.SECOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningResultProcessorTest {

    @Test
    @DisplayName("당첨 결과를 보고 순위별로 몇 번씩 당첨되었는지 계산한다.")
    void calculateWinningCountTest() {
        // given
        DrawResult drawResult1 = DrawResult.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), FIRST);
        DrawResult drawResult2 = DrawResult.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), FIRST);
        DrawResult drawResult3 = DrawResult.of(Lotto.from(List.of(1, 2, 3, 4, 7, 6)), SECOND);
        List<DrawResult> drawResults = List.of(drawResult1, drawResult2, drawResult3);
        WinningResultProcessor winningResultProcessor = new WinningResultProcessor();

        // when
        WinningCountResult winningCountResult = winningResultProcessor.calculateWinningCount(drawResults);

        // then
        Map<Ranking, Integer> winningCounts = winningCountResult.winningCountMap();
        assertThat(winningCounts.get(FIRST)).isEqualTo(2);
        assertThat(winningCounts.get(SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("구입 금액과 순위별 당첨 횟수로 수익률을 계산한다.")
    void calculateReturnRateTest() {
        // given
        WinningCountResult winningCountResult = WinningCountResult.create();
        Map<Ranking, Integer> winningCounts = winningCountResult.winningCountMap();
        winningCounts.put(FIRST, 2); // 1등 2번 당첨
        winningCounts.put(SECOND, 1); // 2등 1번 당첨
        int purchaseAmount = 8000;

        WinningResultProcessor winningResultProcessor = new WinningResultProcessor();

        // when
        double returnRate = winningResultProcessor.calculateReturnRate(purchaseAmount, winningCountResult);

        // then
        assertThat(returnRate).isEqualTo(50375000);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -3000})
    @DisplayName("구입 금액이 0 이하이면 예외가 발생한다.")
    void calculateReturnRateWhenPurchaseAmountIsUnder0(int purchaseAmount) {
        // given
        WinningResultProcessor winningResultProcessor = new WinningResultProcessor();

        // when, then
        assertThatThrownBy(() -> winningResultProcessor.calculateReturnRate(purchaseAmount, WinningCountResult.create()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_DIGIT.getMessage());

    }
}