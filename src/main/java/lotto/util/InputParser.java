package lotto.util;

import static lotto.config.ErrorMessage.WINNING_NUMBER_INVALID_FORMAT;
import static lotto.config.ErrorMessage.WINNING_NUMBER_NOT_NUMERIC;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private static final String SEPARATOR = ",";

    public static List<Integer> parseWinningNumbers(String input) {
        String[] parts = input.split(SEPARATOR, -1);
        List<Integer> numbers = new ArrayList<>();

        for (String part : parts) {
            numbers.add(parseSingleNumber(part));
        }

        return numbers;
    }

    private static int parseSingleNumber(String part) {
        String trimmedPart = part.trim();
        validateIsNotEmpty(trimmedPart);

        return validateIsNumeric(trimmedPart);
    }

    private static void validateIsNotEmpty(String part) {
        if (part.isEmpty()) {
            throw new IllegalArgumentException(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }
    }

    private static int validateIsNumeric(String part) {
        try {
            return Integer.parseInt(part);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WINNING_NUMBER_NOT_NUMERIC.getMessage());
        }
    }
}
