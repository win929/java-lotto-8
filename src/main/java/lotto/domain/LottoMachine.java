package lotto.domain;

import static lotto.config.LottoConfig.LOTTO_MAX_NUMBER;
import static lotto.config.LottoConfig.LOTTO_MIN_NUMBER;
import static lotto.config.LottoConfig.LOTTO_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    public List<Lotto> issueLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(issueSingleLotto());
        }

        return Collections.unmodifiableList(lottos);
    }

    private Lotto issueSingleLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LOTTO_MIN_NUMBER.getValue(),
                LOTTO_MAX_NUMBER.getValue(),
                LOTTO_SIZE.getValue()
        );

        return new Lotto(numbers);
    }
}
