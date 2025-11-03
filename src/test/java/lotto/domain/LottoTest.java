package lotto.domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static lotto.config.ErrorMessage.LOTTO_INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다.")
    void createLotto_InvalidSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void createLotto_DuplicateNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다 - 0")
    void createLotto_RangeOut_Zero() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_INVALID_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다 - 46")
    void createLotto_RangeOut_46() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_INVALID_RANGE.getMessage());
    }

    @DisplayName("두 로또의 일치하는 번호 개수를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6', '1,2,3,10,11,12', 3",
            "'1,2,3,4,5,6', '1,2,3,4,5,6', 6",
            "'1,2,3,4,5,6', '7,8,9,10,11,12', 0"
    })
    void countMatchingNumbers(String userLottoStr, String winningLottoStr, int expectedMatch) {
        Lotto userLotto = new Lotto(parseLottoNumbers(userLottoStr));
        Lotto winningLotto = new Lotto(parseLottoNumbers(winningLottoStr));

        int matchCount = userLotto.countMatchingNumbers(winningLotto);
        assertThat(matchCount).isEqualTo(expectedMatch);
    }

    private List<Integer> parseLottoNumbers(String lottoStr) {
        return Stream.of(lottoStr.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
