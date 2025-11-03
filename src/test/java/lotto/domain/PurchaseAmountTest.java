package lotto.domain;

import static lotto.config.ErrorMessage.AMOUNT_INVALID_UNIT;
import static lotto.config.ErrorMessage.AMOUNT_NEGATIVE_OR_ZERO;
import static lotto.config.ErrorMessage.AMOUNT_NOT_NUMERIC;
import static lotto.config.LottoConfig.LOTTO_PRICE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountTest {
    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    void createPurchaseAmount_NotNumeric() {
        assertThatThrownBy(() -> new PurchaseAmount("1000j"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AMOUNT_NOT_NUMERIC.getMessage());
    }

    @Test
    @DisplayName("구입 금액이 0원이면 예외가 발생한다.")
    void createPurchaseAmount_Zero() {
        assertThatThrownBy(() -> new PurchaseAmount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AMOUNT_NEGATIVE_OR_ZERO.getMessage());
    }

    @Test
    @DisplayName("구입 금액이 1,000원 단위가 아니면 예외가 발생한다.")
    void createPurchaseAmount_InvalidUnit() {
        assertThatThrownBy(() -> new PurchaseAmount("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AMOUNT_INVALID_UNIT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1000", "8000", "12000"})
    @DisplayName("유효한 구입 금액으로 객체를 생성한다.")
    void createPurchaseAmount_Valid(String input) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);
        int expectedLottoCount = Integer.parseInt(input) / LOTTO_PRICE.getValue();

        assertThat(purchaseAmount.getAmount()).isEqualTo(Integer.parseInt(input));
        assertThat(purchaseAmount.getLottoCount()).isEqualTo(expectedLottoCount);
    }
}
