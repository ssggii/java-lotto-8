package lotto.model;

import lotto.dto.DrawResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoDrawerTest {

    @Test
    @DisplayName("당첨 번호와 일치하는 숫자의 개수에 따라 순위를 결정한다.")
    void draw() {
        // given
        LottoDrawer lottoDrawer = new LottoDrawer();
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6)); // 6개 일치
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 4, 8, 7)); // 4개 일치
        List<Lotto> lottos = List.of(lotto1, lotto2);
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);

        // when
        List<DrawResult> drawResults = lottoDrawer.draw(lottos, winningNumbers);

        // then
        assertThat(drawResults.getFirst().hittingNumber()).isEqualTo(6);
        assertThat(drawResults.getLast().hittingNumber()).isEqualTo(4);

    }

}