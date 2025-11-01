package lotto.domain;

import static lotto.config.ErrorMessage.AMOUNT_INVALID_UNIT;
import static lotto.config.ErrorMessage.AMOUNT_NEGATIVE_OR_ZERO;
import static lotto.config.ErrorMessage.AMOUNT_NOT_NUMERIC;
import static lotto.config.LottoConfig.LOTTO_PRICE;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(String input) {
        this.amount = validate(input);
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoCount() {
        return amount / LOTTO_PRICE.getValue();
    }

    private int validate(String input) {
        int number = isNumeric(input);
        isPositive(number);
        isMultipleOfPrice(number);

        return number;
    }

    private int isNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(AMOUNT_NOT_NUMERIC.getMessage());
        }
    }

    private void isPositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(AMOUNT_NEGATIVE_OR_ZERO.getMessage());
        }
    }

    private void isMultipleOfPrice(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(AMOUNT_INVALID_UNIT.getMessage());
        }
    }
}
