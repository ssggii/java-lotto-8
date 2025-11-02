package lotto.controller;

import lotto.global.exception.UserInputException;
import lotto.global.util.UserInputParser;
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
        int purchaseAmount = getValidPurchaseAmount();
    }

    public int getValidPurchaseAmount() {
        while (true) {
            try {
                String purchaseAmountInput = inputView.getPurchaseAmountInput();
                return UserInputParser.parsePurchaseAmount(purchaseAmountInput);
            } catch (UserInputException e) {
                outputView.printErrorMessage(e.getErrorCode().getMessage());
            }
        }
    }

}
