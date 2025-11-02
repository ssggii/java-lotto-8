package lotto.dto;

import lotto.model.Lotto;
import lotto.model.Ranking;

public record DrawResult(
        Lotto lotto, // 추첨 대상인 로또
        Ranking ranking // 당첨 순위
) {
    public static DrawResult of(Lotto lotto, Ranking ranking) {
        return new DrawResult(lotto, ranking);
    }
}
