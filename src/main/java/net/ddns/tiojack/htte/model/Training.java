package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Training {
    GOALKEEPING(9, 0.0510, true),
    DEFENDING(3, 0.0288, true),
    PLAY_MAKING(8, 0.0336, true),
    PLAY_MAKING_LESS(8, 0.0168, false),
    WINGER(5, 0.0480, true),
    WINGER_LESS(5, 0.0240, false),
    PASSING(7, 0.0360, true),
    SCORING(4, 0.0324, true),
    SET_PIECES(2, 0.1470, true),
    SET_PIECES_PLUS(2, 0.18375, false);

    private final int value;
    private final double coefficient;
    private final boolean official;
}
