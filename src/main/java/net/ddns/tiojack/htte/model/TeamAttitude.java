package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TeamAttitude {
    PIC(-1, RatingConfigProp.pic, 0.839949),
    PIN(0, RatingConfigProp.pin, 1.0),
    MOTS(1, RatingConfigProp.mots, 1.109650);

    private final int value;
    private final RatingConfigProp prop;
    private final double factor;
}
