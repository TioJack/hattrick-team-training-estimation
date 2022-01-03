package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Skill {
    GOALKEEPING(0),
    DEFENDING(1),
    WINGER(2),
    PLAY_MAKING(3),
    SCORING(4),
    PASSING(5),
    SET_PIECES(8);

    private final int value;
}
