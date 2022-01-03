package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static net.ddns.tiojack.htte.model.RatingConfigProp.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TeamAttitude {
    PIC(-1, pic),
    PIN(0, pin),
    MOTS(1, mots);

    private final int value;
    private final RatingConfigProp prop;
}
