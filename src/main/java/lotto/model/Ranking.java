package lotto.model;

import java.util.Arrays;
import java.util.Optional;

public enum Ranking {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false);

    private final int hittingCount;
    private final int prize;
    private final boolean hitBonus;

    Ranking(int hittingCount, int prize, boolean hitBonus) {
        this.hittingCount = hittingCount;
        this.prize = prize;
        this.hitBonus = hitBonus;
    }

    public int getHittingCount() {
        return hittingCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isHitBonus() {
        return hitBonus;
    }

    public static Optional<Ranking> findRanking(int hittingCount, boolean hitBonus) {
        // 5개 일치하면 보너스 당첨 여부까지 검사
        if (hittingCount == 5) {
            if (hitBonus) {
                return Optional.of(SECOND);
            }
            return Optional.of(THIRD);
        }

        // 그 외의 경우 숫자 개수로만 순위 결정
        return Arrays.stream(values())
                .filter(ranking -> ranking.getHittingCount() == hittingCount)
                .findFirst();
    }
}
