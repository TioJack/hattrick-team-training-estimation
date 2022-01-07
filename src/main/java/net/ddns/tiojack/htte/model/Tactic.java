package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticAIM;
import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticAOW;
import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticCounter;
import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticCreative;
import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticLongShots;
import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticNormal;
import static net.ddns.tiojack.htte.model.RatingConfigProp.tacticPressing;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Tactic {
    NORMAL(0, tacticNormal),
    PRESSING(1, tacticPressing),
    COUNTER_ATTACKS(2, tacticCounter),
    ATTACK_IN_THE_MIDDLE(3, tacticAIM),
    ATTACK_IN_WINGS(4, tacticAOW),
    PLAY_CREATIVELY(7, tacticCreative),
    LONG_SHOTS(8, tacticLongShots);

    private final int value;
    private final RatingConfigProp prop;
}
