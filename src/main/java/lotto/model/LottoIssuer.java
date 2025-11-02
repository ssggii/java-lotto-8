package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static lotto.global.exception.ErrorCode.NEGATIVE_PURCHASE_AMOUNT;
import static lotto.global.exception.ErrorCode.NOT_DIVIDED_UP_PURCHASE_AMOUNT;
import static lotto.model.Lotto.*;

public class LottoIssuer {

    public static final int DEFAULT_LOTTO_PRICE = 1000;

    public List<Lotto> issue(int purchaseAmount, int lottoPrice) {
        int lottoCount = calculateLottoCount(purchaseAmount, lottoPrice);
        return createLottos(lottoCount);
    }

    private int calculateLottoCount(int purchaseAmount, int lottoPrice) {
        validatePurchaseAmount(purchaseAmount);
        lottoPrice = getLottoPriceOrDefault(lottoPrice);
        return purchaseAmount / lottoPrice;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        validateNegativePurchaseAmount(purchaseAmount);
        validatePurchaseAmountDividedUp(purchaseAmount);
    }

    private void validatePurchaseAmountDividedUp(int purchaseAmount) {
        if (purchaseAmount % DEFAULT_LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(NOT_DIVIDED_UP_PURCHASE_AMOUNT.getMessage());
        }
    }

    private void validateNegativePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException(NEGATIVE_PURCHASE_AMOUNT.getMessage());
        }
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
