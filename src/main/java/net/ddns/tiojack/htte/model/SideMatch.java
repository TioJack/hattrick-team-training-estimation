package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SideMatch {
    HOME(RatingConfigProp.home, 1.199529),
    AWAY_DERBY(RatingConfigProp.awayDerby, 1.113699),
    AWAY(RatingConfigProp.away, 1.0);

    private final RatingConfigProp prop;
    private final double factor;
}
