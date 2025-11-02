package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoDrawerTest {

    @Test
    @DisplayName("당첨 번호와 일치하는 숫자의 개수를 비교하여 당첨 순위를 결정한다.")
    void decideRankings() {
        // given
        LottoDrawer lottoDrawer = new LottoDrawer();
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningCondition winningCondition = WinningCondition.of(winningNumbers, bonusNumber);

        List<Lotto> lottos = new ArrayList<>();
        lottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6))); // 1등
        lottos.add(Lotto.from(List.of(1, 2, 3, 4, 6, 7))); // 2등
        lottos.add(Lotto.from(List.of(1, 2, 3, 4, 6, 8))); // 3등
        lottos.add(Lotto.from(List.of(1, 2, 3, 4, 10, 11))); // 4등
        lottos.add(Lotto.from(List.of(1, 2, 3, 10, 11, 12))); // 5등
        lottos.add(Lotto.from(List.of(1, 2, 10, 11, 12, 13))); // 꽝
        lottos.add(Lotto.from(List.of(1, 14, 10, 11, 12, 13))); // 꽝
        lottos.add(Lotto.from(List.of(15, 21, 10, 11, 12, 13))); // 꽝
        lottos.add(Lotto.from(List.of(15, 21, 10, 11, 12, 7))); // 꽝

        // when
        List<DrawResult> drawResults = lottoDrawer.decideRankings(lottos, winningCondition);

        // then
        assertThat(drawResults.get(0).ranking()).isEqualTo(Ranking.FIRST);
        assertThat(drawResults.get(1).ranking()).isEqualTo(Ranking.SECOND);
        assertThat(drawResults.get(2).ranking()).isEqualTo(Ranking.THIRD);
        assertThat(drawResults.get(3).ranking()).isEqualTo(Ranking.FOURTH);
        assertThat(drawResults.get(4).ranking()).isEqualTo(Ranking.FIFTH);
        assertThat(drawResults.get(5).ranking()).isEqualTo(Ranking.NONE);
        assertThat(drawResults.get(6).ranking()).isEqualTo(Ranking.NONE);
        assertThat(drawResults.get(7).ranking()).isEqualTo(Ranking.NONE);
        assertThat(drawResults.get(8).ranking()).isEqualTo(Ranking.NONE);
    }

}