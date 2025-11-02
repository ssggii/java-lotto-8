package lotto.dto;

import lotto.model.Ranking;

import java.util.List;

public record DrawResult(
        List<Integer> lottoNumbers, // 로또 번호
        Ranking ranking // 당첨 순위
) {
    public static DrawResult of(List<Integer> lottoNumbers, Ranking ranking) {
        return new DrawResult(lottoNumbers, ranking);
    }
}
