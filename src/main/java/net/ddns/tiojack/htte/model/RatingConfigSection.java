package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RatingConfigSection {
    general(null, null),
    goalkeeping_allsides(SkillType.GOALKEEPING, RatingZone.ALLSIDES),
    defending_allsides(SkillType.DEFENDING, RatingZone.ALLSIDES),
    defending_thisside(SkillType.DEFENDING, RatingZone.THISSIDE),
    defending_middle(SkillType.DEFENDING, RatingZone.MIDDLE),
    playmaking_allsides(SkillType.PLAY_MAKING, RatingZone.ALLSIDES),
    passing_middle(SkillType.PASSING, RatingZone.MIDDLE),
    passing_allsides(SkillType.PASSING, RatingZone.ALLSIDES),
    passing_thisside(SkillType.PASSING, RatingZone.THISSIDE),
    passing_otherside(SkillType.PASSING, RatingZone.ALLSIDES),
    winger_thisside(SkillType.WINGER, RatingZone.THISSIDE),
    winger_allsides(SkillType.WINGER, RatingZone.ALLSIDES),
    winger_otherside(SkillType.WINGER, RatingZone.OTHERSIDE),
    scoring_allsides(SkillType.SCORING, RatingZone.ALLSIDES),
    scoring_otherside(SkillType.SCORING, RatingZone.OTHERSIDE),
    scoring_thisside(SkillType.SCORING, RatingZone.THISSIDE),
    aim_aow(null, null),
    counter(null, null),
    pressing(null, null),
    longshots(null, null);

    private final SkillType skillType;
    private final RatingZone zone;
}
