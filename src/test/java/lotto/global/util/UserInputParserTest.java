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
    void testNotIntegerFormatStringTest(String input) {
        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_FORMAT.getMessage());

    }

    @Test
    @DisplayName("구입 금액이 음수인 경우 예외가 발생한다.")
    void testNegativeNumberTest() {
        // given
        String input = "-10";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_INTEGER.getMessage());
    }

    @Test
    @DisplayName("구입 금액이 1000원으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void notDividedUpTest() {
        // given
        String purchaseAmountInput = "3500";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePurchaseAmount(purchaseAmountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_DIVIDED_UP.getMessage());
    }
}