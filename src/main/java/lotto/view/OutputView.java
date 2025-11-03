package lotto.view;

import lotto.dto.WinningCountResult;
import lotto.model.Lotto;
import lotto.model.Ranking;

import java.util.Arrays;
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
        System.out.println(LOTTO_COUNT_INFO.getMessage(lottos.size()));
        lottos.stream().map(Lotto::getNumbers).forEach(System.out::println);
        newLine();
    }

    public void printDrawResults(WinningCountResult result) {
        System.out.println(DRAW_RESULT_INFO.getMessage());
        Map<Ranking, Integer> winningCounts = result.winningCountMap();

        Arrays.stream(Ranking.values())
                .sorted(Comparator.comparingInt(Ranking::ordinal).reversed()) // 순위 역순 정렬
                .forEach(ranking -> {
                    int winningCount = winningCounts.getOrDefault(ranking, 0);
                    System.out.printf(
                            "%s (%,d원) - %,d개\n",
                            ranking.getDescription(),
                            ranking.getPrize(),
                            winningCount
                    );
                });
    }

    public void printReturnRate(double returnRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.", returnRate);
    }
}
