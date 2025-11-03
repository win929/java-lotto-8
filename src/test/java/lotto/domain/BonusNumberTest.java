package lotto.domain;

import static lotto.config.ErrorMessage.BONUS_DUPLICATE;
import static lotto.config.ErrorMessage.BONUS_INVALID_RANGE;
import static lotto.config.ErrorMessage.BONUS_NOT_NUMERIC;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {
    private Lotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("유효한 보너스 번호를 생성한다.")
    void createBonusNumber_Valid() {
        String input = "7";
        BonusNumber bonusNumber = new BonusNumber(input, winningLotto);
        assertThat(bonusNumber.getNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    void createBonusNumber_NotNumeric() {
        String input = "7a";
        assertThatThrownBy(() -> new BonusNumber(input, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NOT_NUMERIC.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다 - 0")
    void createBonusNumber_RangeOut_Zero() {
        String input = "0";
        assertThatThrownBy(() -> new BonusNumber(input, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_INVALID_RANGE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다 - 46")
    void createBonusNumber_RangeOut_46() {
        String input = "46";
        assertThatThrownBy(() -> new BonusNumber(input, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_INVALID_RANGE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void createBonusNumber_Duplicate() {
        String input = "6";
        assertThatThrownBy(() -> new BonusNumber(input, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_DUPLICATE.getMessage());
    }
}
