package lotto.config;

import static lotto.config.LottoConfig.LOTTO_MAX_NUMBER;
import static lotto.config.LottoConfig.LOTTO_MIN_NUMBER;
import static lotto.config.LottoConfig.LOTTO_PRICE;
import static lotto.config.LottoConfig.LOTTO_SIZE;

public enum ErrorMessage {
    AMOUNT_NOT_NUMERIC("숫자만 입력 가능합니다."),
    AMOUNT_NEGATIVE_OR_ZERO("구입 금액은 0보다 커야 합니다."),
    AMOUNT_INVALID_UNIT(String.format("구입 금액은 %,d원 단위여야 합니다.", LOTTO_PRICE.getValue())),

    LOTTO_INVALID_SIZE(String.format("로또 번호는 %d개여야 합니다.", LOTTO_SIZE.getValue())),
    LOTTO_DUPLICATE("로또 번호에 중복된 숫자가 있습니다."),
    LOTTO_INVALID_RANGE(String.format("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", LOTTO_MIN_NUMBER.getValue(), LOTTO_MAX_NUMBER.getValue()));

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
