package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Training {
    GOALKEEPING(9),
    DEFENDING(3),
    PLAY_MAKING(8),
    PLAY_MAKING_LESS(8),
    WINGER(5),
    WINGER_LESS(5),
    PASSING(7),
    SCORING(4),
    SET_PIECES(2),
    SET_PIECES_PLUS(2);

    private final int value;
}
