package lotto.controller;

import lotto.dto.DrawResult;
import lotto.dto.WinningCondition;
import lotto.dto.WinningCountResult;
import lotto.global.exception.UserInputException;
import lotto.global.util.UserInputParser;
import lotto.model.Lotto;
import lotto.model.LottoDrawer;
import lotto.model.LottoIssuer;
import lotto.model.WinningResultProcessor;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static lotto.model.LottoIssuer.DEFAULT_LOTTO_PRICE;

public class LottoMachine {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoIssuer lottoIssuer;
    private final LottoDrawer lottoDrawer;
    private final WinningResultProcessor winningResultProcessor;

    public LottoMachine(InputView inputView, OutputView outputView, LottoIssuer lottoIssuer, LottoDrawer lottoDrawer, WinningResultProcessor winningResultProcessor) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoIssuer = lottoIssuer;
        this.lottoDrawer = lottoDrawer;
        this.winningResultProcessor = winningResultProcessor;
    }

    public void on() {
        int purchaseAmount = getValidPurchaseAmount();

        List<Lotto> lottos = purchaseLottos(purchaseAmount);

        WinningCondition winningCondition = createWinningCondition();

        WinningCountResult winningCounts = getWinningCounts(lottos, winningCondition);

        showReturnRate(purchaseAmount, winningCounts);
    }

    private void showReturnRate(int purchaseAmount, WinningCountResult winningCountResult) {
        double returnRate = winningResultProcessor.calculateReturnRate(purchaseAmount, winningCountResult);
        outputView.printReturnRate(returnRate);
    }

    private WinningCountResult getWinningCounts(List<Lotto> lottos, WinningCondition winningCondition) {
        List<DrawResult> drawResults = lottoDrawer.decideRankings(lottos, winningCondition);
        WinningCountResult winningCounts = winningResultProcessor.calculateWinningCount(drawResults);
        outputView.printDrawResults(winningCounts);
        return winningCounts;
    }

    private WinningCondition createWinningCondition() {
        Set<Integer> winningNumber = getValidWinningNumber();
        int bonusNumber = getValidBonusNumber(winningNumber);
        return WinningCondition.of(winningNumber, bonusNumber);
    }

    private List<Lotto> purchaseLottos(int purchaseAmount) {
        List<Lotto> lottos = lottoIssuer.issue(purchaseAmount, DEFAULT_LOTTO_PRICE);
        outputView.printLottoNumbers(lottos);
        return lottos;
    }

    /**
     * 사용자 입력을 받고, 유효성 검사에 통과할 때까지 재시도하는 공통 메서드
     *
     * @param inputSupplier 사용자 입력 받는 로직
     * @param parser        입력(String)을 받아 파싱/검증하는 로직
     * @param <T>           파싱 후 반환될 타입
     * @return 유효성 검사를 통과한 값
     */
    private <T> T getValidInput(Supplier<String> inputSupplier, Function<String, T> parser) {
        while (true) {
            try {
                String input = inputSupplier.get();
                outputView.newLine();
                return parser.apply(input);
            } catch (UserInputException e) {
                outputView.printErrorMessage(e.getErrorCode().getMessage());
            }
        }
    }

    private int getValidPurchaseAmount() {
        return getValidInput(inputView::getPurchaseAmountInput, UserInputParser::parsePurchaseAmount);
    }

    private Set<Integer> getValidWinningNumber() {
        return getValidInput(inputView::getWinningNumberInput, UserInputParser::parseWinningNumber);
    }

    private int getValidBonusNumber(Set<Integer> winningNumber) {
        return getValidInput(inputView::getBonusNumberInput,
                (input) -> UserInputParser.parseBonusNumber(input, winningNumber));
    }

}
