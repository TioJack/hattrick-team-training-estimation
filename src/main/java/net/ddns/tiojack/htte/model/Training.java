package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Training {
    GOALKEEPING(9, 0.0510),
    DEFENDING(3, 0.0288),
    PLAY_MAKING(8, 0.0336),
    PLAY_MAKING_LESS(8, 0.0168),
    WINGER(5, 0.0480),
    WINGER_LESS(5, 0.0240),
    PASSING(7, 0.0360),
    SCORING(4, 0.0324),
    SET_PIECES(2, 0.1470),
    SET_PIECES_PLUS(2, 0.18375);

    private final int value;
    private final double coefficient;
}
