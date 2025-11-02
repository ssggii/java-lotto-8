package lotto.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.global.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserInputParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"3.5", "3@!", "50.1."})
    @DisplayName("정수 형태가 아닌 구입 금액을 파싱하는 경우 예외가 발생한다.")
    void testNotIntegerFormatPurchaseAmountTest(String input) {
        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_FORMAT.getMessage());

    }

    @Test
    @DisplayName("구입 금액이 음수인 경우 예외가 발생한다.")
    void testNegativePurchaseAmountTest() {
        // given
        String input = "-10";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_DIGIT.getMessage());
    }

    @Test
    @DisplayName("구입 금액이 1000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void notDividedUpTest() {
        // given
        String purchaseAmountInput = "3500";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePurchaseAmount(purchaseAmountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(PURCHASE_AMOUNT_NOT_DIVIDED_UP.getMessage());
    }

    @Test
    @DisplayName("당첨 번호의 개수가 6개가 아니면 예외가 발생한다.")
    void numbersCountTest() {
        // given
        String winningNumberInput = "1, 2, 3, 4, 5";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parseWinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBERS_SIZE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@1,  3, 1, 2, 4, 5", "1--, 2!, 3, 4, 5, 6"})
    @DisplayName("당첨 번호의 숫자가 정수 형태가 아니면 예외가 발생한다.")
    void notNumberFormatWinningNumbersTest(String winningNumberInput) {
        // when, then
        assertThatThrownBy(() -> UserInputParser.parseWinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_FORMAT.getMessage());
    }

    @Test
    @DisplayName("당첨 번호의 숫자가 음수이면 예외가 발생한다.")
    void negativeWinningNumbersTest() {
        // given
        String winningNumberInput = "1, -2, 3, 4, 5, 6";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parseWinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_DIGIT.getMessage());
    }

    @Test
    @DisplayName("당첨 번호의 숫자가 1~45 사이의 범위가 아니면 예외가 발생한다.")
    void winningNumberRangeTest() {
        // given
        String winningNumberInput = "100, 1, 2, 3, 4, 5";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parseWinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBER_RANGE.getMessage());

    }

    @Test
    @DisplayName("보너스 번호가 음수이면 예외가 발생한다.")
    void negativeBonusNumberTest() {
        // given
        String bonusNumberInput = "-7";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parseBonusNumber(bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_DIGIT.getMessage());

    }

    @Test
    @DisplayName("보너스 번호가 1~45 사이의 범위가 아니면 예외가 발생한다.")
    void BonusNumberOutOfRangeTest() {
        // given
        String bonusNumberInput = "46";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parseBonusNumber(bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBER_RANGE.getMessage());
    }
}