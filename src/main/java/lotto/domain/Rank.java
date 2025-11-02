package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L, "6개 일치 (%,d원)"),
    SECOND(5, 30_000_000L, "5개 일치, 보너스 볼 일치 (%,d원)"),
    THIRD(5, 1_500_000L, "5개 일치 (%,d원)"),
    FOURTH(4, 50_000L, "4개 일치 (%,d원)"),
    FIFTH(3, 5_000L, "3개 일치 (%,d원)"),
    MISS(0, 0L, "");

    private final int matchCount;
    private final long prizeMoney;
    private final String message;

    Rank(int matchCount, long prizeMoney, String message) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.message = message;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public String getMessage() {
        return String.format(message, prizeMoney);
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        if (matchCount == FIRST.matchCount) {
            return FIRST;
        }
        if (matchCount == SECOND.matchCount) {
            return handleSecondAndThird(bonusMatch);
        }
        if (matchCount == FOURTH.matchCount) {
            return FOURTH;
        }
        if (matchCount == FIFTH.matchCount) {
            return FIFTH;
        }

        return MISS;
    }

    private static Rank handleSecondAndThird(boolean bonusMatch) {
        if (bonusMatch) {
            return SECOND;
        }
        return THIRD;
    }
}
