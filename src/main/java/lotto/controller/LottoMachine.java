package lotto.controller;

import lotto.model.LottoDrawer;
import lotto.model.LottoIssuer;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoIssuer lottoIssuer;
    private final LottoDrawer lottoDrawer;

    public LottoMachine(InputView inputView, OutputView outputView, LottoIssuer lottoIssuer, LottoDrawer lottoDrawer) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoIssuer = lottoIssuer;
        this.lottoDrawer = lottoDrawer;
    }

    public void on() {
        String purchaseAmountInput = inputView.getPurchaseAmountInput();

    }

}
