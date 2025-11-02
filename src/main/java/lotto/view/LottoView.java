package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class LottoView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다.";

    public String readInput() {
        return Console.readLine();
    }

    public void printPurchaseAmountPrompt() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printLottoCount(int count) {
        System.out.println();
        System.out.println(String.format(LOTTO_COUNT_MESSAGE, count));
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(formatLottoNumbers(lotto.getNumbers()));
        }
    }

    private String formatLottoNumbers(List<Integer> numbers) {
        String formattedNumbers = numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return "[" + formattedNumbers + "]";
    }
}
