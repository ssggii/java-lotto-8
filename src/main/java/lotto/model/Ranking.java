package lotto.model;

import java.util.Arrays;

public enum Ranking {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(-1, -1, false);

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

    public static Ranking from(int hittingCount, boolean hitBonus) {
        return Arrays.stream(values())
                .filter(ranking -> ranking.hittingCount == hittingCount && ranking.hitBonus == hitBonus)
                .findFirst()
                .orElse(NONE);
    }
}
