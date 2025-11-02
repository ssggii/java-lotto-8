package lotto.model;

import lotto.dto.DrawResult;
import lotto.dto.WinningCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoDrawer {

    public List<DrawResult> decideRankings(List<Lotto> lottos, WinningCondition winningCondition) {
        List<DrawResult> drawResults = new ArrayList<>(lottos.size());
        Set<Integer> winningNumbers = winningCondition.winningNumbers();

        lottos.forEach(lotto -> {
            int hittingCount = lotto.findHittingNumberCount(winningNumbers);
            boolean hitBonus = decideBonusHit(winningCondition, lotto, hittingCount);
            Ranking ranking = Ranking.from(hittingCount, hitBonus);
            drawResults.add(DrawResult.of(lotto.getNumbers(), ranking));
        });

        return drawResults;
    }

    private boolean decideBonusHit(WinningCondition winningCondition, Lotto lotto, int hittingCount) {
        boolean hitBonus = false;
        if (hittingCount == 5) {
            hitBonus = lotto.isHitBonusNumber(winningCondition);
        }
        return hitBonus;
    }

}
