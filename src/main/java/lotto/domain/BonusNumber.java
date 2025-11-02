package lotto.domain;

import static lotto.config.ErrorMessage.BONUS_DUPLICATE;
import static lotto.config.ErrorMessage.BONUS_INVALID_RANGE;
import static lotto.config.ErrorMessage.BONUS_NOT_NUMERIC;
import static lotto.config.LottoConfig.LOTTO_MAX_NUMBER;
import static lotto.config.LottoConfig.LOTTO_MIN_NUMBER;

public class BonusNumber {
    private final int number;

    public BonusNumber(String input, Lotto winningLotto) {
        this.number = validate(input, winningLotto);
    }

    public int getNumber() {
        return number;
    }

    private int validate(String input, Lotto winningLotto) {
        int number = validateIsNumeric(input);
        validateRange(number);
        validateIsDuplicate(number, winningLotto);

        return number;
    }

    private int validateIsNumeric(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BONUS_NOT_NUMERIC.getMessage());
        }
    }

    private void validateRange(int number) {
        if (number < LOTTO_MIN_NUMBER.getValue() || number > LOTTO_MAX_NUMBER.getValue()) {
            throw new IllegalArgumentException(BONUS_INVALID_RANGE.getMessage());
        }
    }

    private void validateIsDuplicate(int number, Lotto winningLotto) {
        if (winningLotto.contains(number)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE.getMessage());
        }
    }
}
