package lotto.dto;

import java.util.List;

public record DrawResult(
        List<Integer> lottoNumbers, // 로또 번호
        int hittingNumber // 당첨 번호와 일치하는 숫자의 개수
) {
    public static DrawResult of(List<Integer> lottoNumbers, int hittingNumber) {
        return new DrawResult(lottoNumbers, hittingNumber);
    }
}
