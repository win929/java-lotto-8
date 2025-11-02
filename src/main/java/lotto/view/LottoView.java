package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class LottoView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String WINNING_NUMBER_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "보너스 번호를 입력해 주세요.";
    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String SEPARATOR_LINE = "---";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %,.1f%%입니다.";

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

    public void printWinningNumberPrompt() {
        System.out.println();
        System.out.println(WINNING_NUMBER_PROMPT);
    }

    public void printBonusNumberPrompt() {
        System.out.println();
        System.out.println(BONUS_NUMBER_PROMPT);
    }

    public void printStatistics(LottoResult lottoResult) {
        System.out.println();
        System.out.println(STATISTICS_HEADER);
        System.out.println(SEPARATOR_LINE);

        Map<Rank, Integer> statistics = lottoResult.getStatistics();

        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .sorted(Comparator.comparing(Rank::getPrizeMoney))
                .forEach(rank -> {
                    System.out.printf("%s - %d개%n", rank.getMessage(), statistics.get(rank));
                });
    }

    public void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_RATE_MESSAGE, profitRate));
    }

    private String formatLottoNumbers(List<Integer> numbers) {
        String formattedNumbers = numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return "[" + formattedNumbers + "]";
    }
}
