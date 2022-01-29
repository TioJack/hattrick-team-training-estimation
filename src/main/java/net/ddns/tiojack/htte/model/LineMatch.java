package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LineMatch {
    DEFENSE(0.9, 1.15),
    MIDFIELD(1.0, 1.0),
    ATTACK(1.1, 0.9);

    private final double trainerOffensiveFactor;
    private final double trainerDefensiveFactor;
}
