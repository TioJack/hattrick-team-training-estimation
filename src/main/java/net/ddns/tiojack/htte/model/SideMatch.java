package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SideMatch {
    HOME(RatingConfigProp.home),
    AWAY_DERBY(RatingConfigProp.awayDerby),
    AWAY(RatingConfigProp.away);

    private final RatingConfigProp prop;
}
