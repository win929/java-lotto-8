package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> statistics;

    public LottoResult(List<Lotto> userLottos, Lotto winningLotto, BonusNumber bonusNumber) {
        this.statistics = new EnumMap<>(Rank.class);
        initializeStatistics();
        calculateStatistics(userLottos, winningLotto, bonusNumber);
    }

    private void initializeStatistics() {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    private void calculateStatistics(List<Lotto> userLottos, Lotto winningLotto, BonusNumber bonusNumber) {
        for (Lotto lotto : userLottos) {
            Rank rank = determineRank(lotto, winningLotto, bonusNumber);
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    private Rank determineRank(Lotto lotto, Lotto winningLotto, BonusNumber bonusNumber) {
        int matchCount = lotto.countMatchingNumbers(winningLotto);
        boolean bonusMatch = lotto.contains(bonusNumber.getNumber());

        return Rank.of(matchCount, bonusMatch);
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        if (purchaseAmount == 0) {
            return 0.0;
        }

        return (double) totalPrize / purchaseAmount * 100.0;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0L;
        for (Rank rank : statistics.keySet()) {
            totalPrize += rank.getPrizeMoney() * statistics.get(rank);
        }

        return totalPrize;
    }
}
