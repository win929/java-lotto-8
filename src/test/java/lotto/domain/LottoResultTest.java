package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private Lotto winningLotto;
    private BonusNumber bonusNumber;

    @BeforeEach
    void setUp() {
        winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        bonusNumber = new BonusNumber("7", winningLotto);
    }

    @Test
    @DisplayName("당첨 통계를 정확히 계산한다.")
    void calculateStatistics() {
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 12)), // 3등
                new Lotto(List.of(1, 2, 3, 4, 11, 12)), // 4등
                new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 5등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
        );

        LottoResult lottoResult = new LottoResult(userLottos, winningLotto, bonusNumber);
        Map<Rank, Integer> statistics = lottoResult.getStatistics();

        assertThat(statistics.get(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.get(Rank.THIRD)).isEqualTo(1);
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(statistics.get(Rank.MISS)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 정확히 계산한다. (ApplicationTest 예시)")
    void calculateProfitRate_Example() {
        Lotto winningLottoExample = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumberExample = new BonusNumber("7", winningLottoExample);

        List<Lotto> userLottos = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)), // 꽝
                new Lotto(List.of(3, 5, 11, 16, 32, 38)), // 꽝
                new Lotto(List.of(7, 11, 16, 35, 36, 44)), // 꽝
                new Lotto(List.of(1, 8, 11, 31, 41, 42)), // 꽝
                new Lotto(List.of(13, 14, 16, 38, 42, 45)), // 꽝
                new Lotto(List.of(7, 11, 30, 40, 42, 43)), // 꽝
                new Lotto(List.of(2, 13, 22, 32, 38, 45)), // 꽝
                new Lotto(List.of(1, 3, 5, 14, 22, 45))  // 5등 (1, 3, 5 일치)
        );

        int purchaseAmount = 8000;
        LottoResult lottoResult = new LottoResult(userLottos, winningLottoExample, bonusNumberExample);
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        // 5000 (당첨금) / 8000 (구매금액) * 100 = 62.5
        assertThat(profitRate).isEqualTo(62.5, offset(0.01));
    }
}
