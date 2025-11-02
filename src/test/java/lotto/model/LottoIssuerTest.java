package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.global.exception.ErrorCode.NEGATIVE_PURCHASE_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoIssuerTest {

    @Test
    @DisplayName("로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.")
    void lottoIssuerTest() {
        // given
        int purchaseAmount = 3000;
        int lottoPrice = 1000;
        int expectedLottoCount = purchaseAmount / lottoPrice;
        LottoIssuer lottoIssuer = new LottoIssuer();

        // when
        List<Lotto> purchasedLottos = lottoIssuer.issue(purchaseAmount, lottoPrice);

        // then
        assertEquals(expectedLottoCount, purchasedLottos.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("로또 가격이 0 이하이면, 로또 가격을 1000원으로 계산한다.")
    void NegativeLottoPriceTest(int lottoPrice) {
        // given
        int purchaseAmount = 3000;
        LottoIssuer lottoIssuer = new LottoIssuer();

        // when
        List<Lotto> purchasedLottos = lottoIssuer.issue(purchaseAmount, lottoPrice);

        // then
        assertThat(purchasedLottos).hasSize(3); // 구매한 로또는 3장이어야함
    }

    @Test
    @DisplayName("구입 금액이 음수이면 예외가 발생한다.")
    void negativePurchaseAmountTest() {
        // given
        int purchaseAmount = -3000;
        int lottoPrice = 1000;
        LottoIssuer lottoIssuer = new LottoIssuer();

        // when, then
        assertThatThrownBy(() -> lottoIssuer.issue(purchaseAmount, lottoPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_PURCHASE_AMOUNT.getMessage());

    }

    @Test
    @DisplayName("구입 금액이 1000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void notDividedUp() {
        // given
        int purchaseAmount = 3500;
        int lottoPrice = 1000;
        LottoIssuer lottoIssuer = new LottoIssuer();

        // when, then
        assertThatThrownBy(() -> lottoIssuer.issue(purchaseAmount, lottoPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구입 금액을 1000원 단위로 입력해주세요");
    }
}