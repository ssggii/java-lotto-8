package lotto.view;

import lotto.dto.WinningCountResult;
import lotto.model.Lotto;
import lotto.model.Ranking;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static lotto.global.ViewMessage.DRAW_RESULT_INFO;
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
        newLine();
    }

    public void printDrawResults(WinningCountResult result) {
        System.out.println(DRAW_RESULT_INFO.getMessage());

        Map<Ranking, Integer> winningCounts = result.winningCountMap();
        winningCounts.keySet().stream()
                .sorted(Comparator.comparingInt(Ranking::ordinal).reversed())
                .forEach(ranking -> {
                    int winningCount = winningCounts.getOrDefault(ranking, 0);
                    int hittingCount = ranking.getHittingCount();
                    int prize = ranking.getPrize();
                    boolean isHitBonus = ranking.isHitBonus();

                    StringBuilder builder = new StringBuilder("%,d개 일치 (%,d원) - %,d개\n");
                    if (isHitBonus) {
                        builder.insert(7, ", 보너스볼 일치");
                    }
                    System.out.printf(builder.toString(), hittingCount, prize, winningCount);
                });
    }
}
