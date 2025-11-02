package lotto.model;

import lotto.dto.AllWinningNumbers;
import lotto.dto.DrawResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoDrawer {

    public List<DrawResult> decideRankings(List<Lotto> lottos, AllWinningNumbers allWinningNumbers) {
        List<DrawResult> drawResults = new ArrayList<>(lottos.size());
        Set<Integer> winningNumbers = allWinningNumbers.winningNumbers();

        lottos.forEach(lotto -> {
            int hittingCount = lotto.findHittingNumberCount(winningNumbers);
            boolean hitBonus = decideBonusHit(allWinningNumbers, lotto, hittingCount);
            Ranking ranking = Ranking.from(hittingCount, hitBonus);
            drawResults.add(DrawResult.of(lotto.getNumbers(), ranking));
        });

        return drawResults;
    }

    private boolean decideBonusHit(AllWinningNumbers allWinningNumbers, Lotto lotto, int hittingCount) {
        boolean hitBonus = false;
        if (hittingCount == 5) {
            hitBonus = lotto.isHitBonusNumber(allWinningNumbers);
        }
        return hitBonus;
    }

}
