package lotto;

import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static lotto.global.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다.")
    void lottoNumbersCountTest() {
        // given
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6, 7); // 로또 번호 개수가 6 초과인 경우
        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5); // 로또 번호 개수가 6 미만인 경우

        // when, then
        assertThatThrownBy(() -> Lotto.from(numbers1))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(INVALID_NUMBERS_SIZE.getMessage());
        assertThatThrownBy(() -> Lotto.from(numbers2))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(INVALID_NUMBERS_SIZE.getMessage());
    }

    @Test
    @DisplayName("로또 번호의 숫자가 1~45 사이의 범위가 아니면 예외가 발생한다.")
    void lottoNumbersRangeTest() {
        // given
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 46); // 46이 범위를 벗어남
        List<Integer> numbers2 = List.of(0, 2, 3, 4, 5, 45); // 0이 범위를 벗어남

        // when, then
        assertRandomUniqueNumbersInRangeTest(
                () -> assertThatThrownBy(() -> Lotto.from(numbers1))
                        .isInstanceOf(IllegalArgumentException.class).hasMessage(INVALID_NUMBER_RANGE.getMessage()),
                numbers1
        );
        assertRandomUniqueNumbersInRangeTest(
                () -> assertThatThrownBy(() -> Lotto.from(numbers2))
                        .isInstanceOf(IllegalArgumentException.class).hasMessage(INVALID_NUMBER_RANGE.getMessage()),
                numbers2
        );
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다.")
    void uniqueLottoNumbersTest() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // when, then
        assertRandomUniqueNumbersInRangeTest(
                () -> assertThatThrownBy(() -> Lotto.from(numbers))
                        .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_UNIQUE_NUMBERS.getMessage()),
                numbers
        );
    }
}
