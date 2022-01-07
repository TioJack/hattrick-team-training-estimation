package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TeamAttitude {
    PIC(-1, RatingConfigProp.pic),
    PIN(0, RatingConfigProp.pin),
    MOTS(1, RatingConfigProp.mots);

    private final int value;
    private final RatingConfigProp prop;
}
