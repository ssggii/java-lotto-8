package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static lotto.model.Lotto.*;

public class LottoIssuer {

    public static final int DEFAULT_LOTTO_PRICE = 1000;

    public List<Lotto> issue(int purchaseAmount, int lottoPrice) {
        lottoPrice = getLottoPriceOrDefault(lottoPrice);
        int lottoCount = purchaseAmount / lottoPrice;
        return createLottos(lottoCount);
    }

    private int getLottoPriceOrDefault(int lottoPrice) {
        if (lottoPrice <= 0) {
            lottoPrice = DEFAULT_LOTTO_PRICE;
        }
        return lottoPrice;
    }

    private List<Lotto> createLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>(lottoCount);
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(NUMBER_RANGE_MIN, NUMBER_RANGE_MAX, NUMBERS_SIZE);
            Lotto newLotto = Lotto.from(numbers);
            lottos.add(newLotto);
        }
        return lottos;
    }

}
