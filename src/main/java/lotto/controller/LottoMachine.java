package lotto.controller;

import lotto.dto.DrawResult;
import lotto.dto.WinningCondition;
import lotto.global.exception.UserInputException;
import lotto.global.util.UserInputParser;
import lotto.model.Lotto;
import lotto.model.LottoDrawer;
import lotto.model.LottoIssuer;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Set;

import static lotto.model.LottoIssuer.DEFAULT_LOTTO_PRICE;

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

        List<Lotto> lottos = lottoIssuer.issue(purchaseAmount, DEFAULT_LOTTO_PRICE);
        outputView.printLottoNumbers(lottos);

        Set<Integer> winningNumber = getValidWinningNumber();
        int bonusNumber = getValidBonusNumber(winningNumber);
        WinningCondition winningCondition = WinningCondition.of(winningNumber, bonusNumber);

        List<DrawResult> drawResults = lottoDrawer.decideRankings(lottos, winningCondition);
//        outputView.printDrawResults(drawResults);

    }

    private int getValidPurchaseAmount() {
        while (true) {
            try {
                String purchaseAmountInput = inputView.getPurchaseAmountInput();
                outputView.newLine();
                return UserInputParser.parsePurchaseAmount(purchaseAmountInput);
            } catch (UserInputException e) {
                outputView.printErrorMessage(e.getErrorCode().getMessage());
            }
        }
    }

    private Set<Integer> getValidWinningNumber() {
        while (true) {
            try {
                String winningNumberInput = inputView.getWinningNumberInput();
                outputView.newLine();
                return UserInputParser.parseWinningNumber(winningNumberInput);
            } catch (UserInputException e) {
                outputView.printErrorMessage(e.getErrorCode().getMessage());
            }
        }
    }

    private int getValidBonusNumber(Set<Integer> winningNumber) {
        while (true) {
            try {
                String bonusNumberInput = inputView.getBonusNumberInput();
                outputView.newLine();
                return UserInputParser.parseBonusNumber(bonusNumberInput, winningNumber);
            } catch (UserInputException e) {
                outputView.printErrorMessage(e.getErrorCode().getMessage());
            }
        }
    }

}
