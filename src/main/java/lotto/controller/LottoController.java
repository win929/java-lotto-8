package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.view.LottoView;

public class LottoController {
    private final LottoView lottoView;

    public LottoController() {
        this.lottoView = new LottoView();
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
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
