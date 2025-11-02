package lotto;

import lotto.controller.LottoMachine;
import lotto.model.LottoDrawer;
import lotto.model.LottoIssuer;
import lotto.model.WinningResultProcessor;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoIssuer lottoIssuer = new LottoIssuer();
        LottoDrawer lottoDrawer = new LottoDrawer();
        WinningResultProcessor winningResultProcessor = new WinningResultProcessor();
        LottoMachine lottoMachine = new LottoMachine(inputView, outputView, lottoIssuer, lottoDrawer, winningResultProcessor);

        lottoMachine.on();
    }
}
