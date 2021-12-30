package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {
    KEEPER(100, "GK", true, true, true),
    RIGHT_BACK(101, "WB", false, false, true),
    RIGHT_CENTRAL_DEFENDER(102, "CD", false, false, true),
    MIDDLE_CENTRAL_DEFENDER(103, "CD", false, true, false),
    LEFT_CENTRAL_DEFENDER(104, "CD", true, false, false),
    LEFT_BACK(105, "WB", true, false, false),
    RIGHT_WINGER(106, "WI", false, false, true),
    RIGHT_INNER_MIDFIELD(107, "IM", false, false, true),
    MIDDLE_INNER_MIDFIELD(108, "IM", false, true, false),
    LEFT_INNER_MIDFIELD(109, "IM", true, false, false),
    LEFT_WINGER(110, "WI", true, false, false),
    RIGHT_FORWARD(111, "FW", false, false, true),
    MIDDLE_FORWARD(112, "FW", false, true, false),
    LEFT_FORWARD(113, "FW", true, false, false),
    SUBSTITUTION_KEEPER(114, "", false, false, false),
    SUBSTITUTION_DEFENDER(115, "", false, false, false),
    SUBSTITUTION_INNER_MIDFIELD(116, "", false, false, false),
    SUBSTITUTION_WINGER(117, "", false, false, false),
    SUBSTITUTION_FORWARD(118, "", false, false, false),
    SUBSTITUTION__KEEPER(200, "", false, false, false),
    SUBSTITUTION__CENTRAL_DEFENDER(201, "", false, false, false),
    SUBSTITUTION__WING_BACK(202, "", false, false, false),
    SUBSTITUTION__INNER_MIDFIELDER(203, "", false, false, false),
    SUBSTITUTION__FORWARD(204, "", false, false, false),
    SUBSTITUTION__WINGER(205, "", false, false, false),
    SUBSTITUTION__EXTRA(206, "", false, false, false),
    BACKUP_KEEPER(207, "", false, false, false),
    BACKUP_CENTRAL_DEFENDER(208, "", false, false, false),
    BACKUP_WING_BACK(209, "", false, false, false),
    BACKUP_INNER_MIDFIELDER(210, "", false, false, false),
    BACKUP_FORWARD(211, "", false, false, false),
    BACKUP_WINGER(212, "", false, false, false),
    BACKUP_EXTRA(213, "", false, false, false),
    SET_PIECES(17, "", false, false, false),
    CAPTAIN(18, "", false, false, false),
    REPLACED_PLAYER_1(19, "", false, false, false),
    REPLACED_PLAYER_2(20, "", false, false, false),
    REPLACED_PLAYER_3(21, "", false, false, false),
    PENALTY_TAKER_1(22, "", false, false, false),
    PENALTY_TAKER_2(23, "", false, false, false),
    PENALTY_TAKER_3(24, "", false, false, false),
    PENALTY_TAKER_4(25, "", false, false, false),
    PENALTY_TAKER_5(26, "", false, false, false),
    PENALTY_TAKER_6(27, "", false, false, false),
    PENALTY_TAKER_7(28, "", false, false, false),
    PENALTY_TAKER_8(29, "", false, false, false),
    PENALTY_TAKER_9(30, "", false, false, false),
    PENALTY_TAKER_10(31, "", false, false, false),
    PENALTY_TAKER_11(32, "", false, false, false);

    private final int value;
    private final String abr;
    private final boolean left;
    private final boolean middle;
    private final boolean right;
}
