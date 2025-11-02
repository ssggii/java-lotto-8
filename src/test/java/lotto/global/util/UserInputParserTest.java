package lotto.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserInputParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"3.5", "3@!", "50.1."})
    @DisplayName("정수 형태가 아닌 문자열을 파싱하는 경우 예외가 발생한다.")
    void testNotIntegerFormatStringTest(String input) {
        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePositiveInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수로 변환할 수 없습니다");

    }

    @Test
    @DisplayName("변환한 값이 음수인 경우 예외가 발생한다.")
    void testNegativeNumberTest() {
        // given
        String input = "-10";

        // when, then
        assertThatThrownBy(() -> UserInputParser.parsePositiveInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("변환값이 음수입니다");
    }

}