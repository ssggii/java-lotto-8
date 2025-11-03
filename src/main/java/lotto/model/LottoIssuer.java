package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static lotto.global.util.InputValidator.validateNegativeNumber;
import static lotto.global.util.InputValidator.validateNotDividedUp;
import static lotto.model.Lotto.*;

public class LottoIssuer {

    public static final int DEFAULT_LOTTO_PRICE = 1000;

    public List<Lotto> issue(int purchaseAmount, int lottoPrice) {
        int lottoCount = calculateLottoCount(purchaseAmount, lottoPrice);
        return createLottos(lottoCount);
    }

    private int calculateLottoCount(int purchaseAmount, int lottoPrice) {
        lottoPrice = getLottoPriceOrDefault(lottoPrice);
        validate(purchaseAmount, lottoPrice);
        return purchaseAmount / lottoPrice;
    }

    private void validate(int purchaseAmount, int lottoPrice) {
        validateNegativeNumber(purchaseAmount);
        validateNotDividedUp(purchaseAmount, lottoPrice);
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
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(NUMBER_RANGE_MIN, NUMBER_RANGE_MAX, LOTTO_NUMBERS_SIZE);
            Lotto newLotto = Lotto.from(numbers);
            lottos.add(newLotto);
        }
        return lottos;
    }

}
