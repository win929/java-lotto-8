package lotto.util;

import static lotto.config.ErrorMessage.WINNING_NUMBER_INVALID_FORMAT;
import static lotto.config.ErrorMessage.WINNING_NUMBER_NOT_NUMERIC;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {
    @Test
    @DisplayName("정상적인 당첨 번호 입력을 파싱한다.")
    void parseWinningNumbers_Valid() {
        String input = "1,2,3,4,5,6";
        List<Integer> result = InputParser.parseWinningNumbers(input);
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("공백이 포함된 당첨 번호 입력을 파싱한다.")
    void parseWinningNumbers_WithSpaces() {
        String input = " 1, 2 ,3,4, 5 , 6 ";
        List<Integer> result = InputParser.parseWinningNumbers(input);
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,a,5,6", "1, 2, 3, 4, 5, 6a"})
    @DisplayName("숫자가 아닌 값이 포함되면 예외가 발생한다.")
    void parseWinningNumbers_NotNumeric(String input) {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBER_NOT_NUMERIC.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,", ",1,2,3,4,5", "1,2,,4,5,6"})
    @DisplayName("비어있는 값이 포함되면(잘못된 쉼표 사용) 예외가 발생한다.")
    void parseWinningNumbers_InvalidFormat_EmptyPart(String input) {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBER_INVALID_FORMAT.getMessage());
    }
}
