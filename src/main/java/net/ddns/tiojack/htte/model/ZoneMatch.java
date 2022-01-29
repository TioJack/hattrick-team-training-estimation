package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static net.ddns.tiojack.htte.model.LineMatch.ATTACK;
import static net.ddns.tiojack.htte.model.LineMatch.DEFENSE;
import static net.ddns.tiojack.htte.model.ZoneSideMatch.CENTRAL;
import static net.ddns.tiojack.htte.model.ZoneSideMatch.LEFT;
import static net.ddns.tiojack.htte.model.ZoneSideMatch.RIGHT;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ZoneMatch {
    MIDFIELD(LineMatch.MIDFIELD, CENTRAL),
    RIGHT_DEFENSE(DEFENSE, RIGHT),
    CENTRAL_DEFENSE(DEFENSE, CENTRAL),
    LEFT_DEFENSE(DEFENSE, LEFT),
    RIGHT_ATTACK(ATTACK, RIGHT),
    CENTRAL_ATTACK(ATTACK, CENTRAL),
    LEFT_ATTACK(ATTACK, LEFT);

    private final LineMatch line;
    private final ZoneSideMatch side;
}
