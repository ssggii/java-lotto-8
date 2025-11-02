package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCountResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static lotto.model.Ranking.FIRST;
import static lotto.model.Ranking.SECOND;
import static org.assertj.core.api.Assertions.assertThat;

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

}