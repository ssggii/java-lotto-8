package lotto.view;

import lotto.model.Lotto;

import java.util.List;

import static lotto.global.ViewMessage.LOTTO_COUNT_INFO;

public class OutputView {

    public void newLine() {
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printLottoNumbers(List<Lotto> lottos) {
        String lottoCountMessage = LOTTO_COUNT_INFO.getMessage(lottos.size());
        System.out.println(lottoCountMessage);
        lottos.stream().map(Lotto::getNumbers).forEach(System.out::println);
    }

}
