package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {
    @DisplayName("일치 개수와 보너스 여부로 정확한 등수를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "6, true, FIRST",
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, true, FOURTH",
            "4, false, FOURTH",
            "3, true, FIFTH",
            "3, false, FIFTH",
            "2, true, MISS",
            "2, false, MISS",
            "1, true, MISS",
            "1, false, MISS",
            "0, true, MISS",
            "0, false, MISS"
    })
    void of(int matchCount, boolean bonusMatch, Rank expectedRank) {
        Rank result = Rank.of(matchCount, bonusMatch);
        assertThat(result).isEqualTo(expectedRank);
    }
}
