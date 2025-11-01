package lotto.config;

import static lotto.config.LottoConfig.LOTTO_PRICE;

public enum ErrorMessage {
    AMOUNT_NOT_NUMERIC("숫자만 입력 가능합니다."),
    AMOUNT_NEGATIVE_OR_ZERO("구입 금액은 0보다 커야 합니다."),
    AMOUNT_INVALID_UNIT(String.format("구입 금액은 %,d원 단위여야 합니다.", LOTTO_PRICE.getValue()));

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
