package lotto.model;

import lotto.dto.DrawResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoDrawer {

    public List<DrawResult> draw(List<Lotto> lottos, Set<Integer> winningNumbers) {
        List<DrawResult> drawResults = new ArrayList<>(lottos.size());
        lottos.forEach(lotto -> {
            int hittingNumberCount = lotto.getHittingNumberCount(winningNumbers);
            drawResults.add(DrawResult.of(lotto.getNumbers(), hittingNumberCount));
        });
        return drawResults;
    }

}
