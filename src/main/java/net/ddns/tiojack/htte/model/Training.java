package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Training {
    NO_TRAINING(0, 0.0, false, null),
    GOALKEEPING(9, 0.0510, true, Skill.GOALKEEPING),
    DEFENDING(3, 0.0288, true, Skill.DEFENDING),
    PLAY_MAKING(8, 0.0336, true, Skill.PLAY_MAKING),
    PLAY_MAKING_LESS(8, 0.0168, false, Skill.PLAY_MAKING),
    WINGER(5, 0.0480, true, Skill.WINGER),
    WINGER_LESS(5, 0.0240, false, Skill.WINGER),
    PASSING(7, 0.0360, true, Skill.PASSING),
    SCORING(4, 0.0324, true, Skill.SCORING),
    SET_PIECES(2, 0.1470, true, Skill.SET_PIECES),
    SET_PIECES_PLUS(2, 0.18375, false, Skill.SET_PIECES);

    private final int value;
    private final double coefficient;
    private final boolean official;
    private final Skill skill;
}
