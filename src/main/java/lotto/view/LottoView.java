package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class LottoView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";

    public String readInput() {
        return Console.readLine();
    }

    public void printPurchaseAmountPrompt() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
