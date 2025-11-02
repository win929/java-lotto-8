package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.PurchaseAmount;
import lotto.view.LottoView;

public class LottoController {
    private final LottoView lottoView;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.lottoView = new LottoView();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();

        int lottoCount = purchaseAmount.getLottoCount();
        lottoView.printLottoCount(lottoCount);

        List<Lotto> lottos = lottoMachine.issueLottos(lottoCount);
        lottoView.printLottos(lottos);
    }

    private PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                lottoView.printPurchaseAmountPrompt();

                return new PurchaseAmount(lottoView.readInput());
            } catch (IllegalArgumentException e) {
                lottoView.printErrorMessage(e.getMessage());
            }
        }
    }
}
