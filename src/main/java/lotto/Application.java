package lotto;

import lotto.controller.LottoMachine;
import lotto.model.LottoDrawer;
import lotto.model.LottoIssuer;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoIssuer lottoIssuer = new LottoIssuer();
        LottoDrawer lottoDrawer = new LottoDrawer();
        LottoMachine lottoMachine = new LottoMachine(inputView, outputView, lottoIssuer, lottoDrawer);

        lottoMachine.on();
    }
}
