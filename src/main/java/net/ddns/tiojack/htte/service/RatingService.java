package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static net.ddns.tiojack.htte.model.Behaviour.NORMAL;
import static net.ddns.tiojack.htte.model.RatingConfigGroup.PLAYERSTRENGTH;
import static net.ddns.tiojack.htte.model.RatingConfigProp.*;
import static net.ddns.tiojack.htte.model.RatingConfigSection.general;
import static net.ddns.tiojack.htte.model.RatingZone.*;
import static net.ddns.tiojack.htte.model.Role.KEEPER;

@Service
@RequiredArgsConstructor
public class RatingService {

    public String getRatings() {

        final Lineup startingLineup = Lineup.builder()
                .fieldPlayers(Collections.singletonList(LineupPlayer.builder()
                        .player(Player.builder()
                                .id(1)
                                .form(5)
                                .stamina(8)
                                .keeper(15)
                                .playmaker(5)
                                .scorer(4)
                                .passing(2)
                                .winger(3)
                                .defender(13)
                                .setPieces(5)
                                .experience(12)
                                .loyalty(20)
                                .motherClubBonus(true)
                                .build())
//                        .player(Player.builder()
//                                .id(2)
//                                .form(7)
//                                .stamina(7)
//                                .keeper(13)
//                                .playmaker(6)
//                                .scorer(5)
//                                .passing(4)
//                                .winger(3)
//                                .defender(8)
//                                .setPieces(4)
//                                .experience(5)
//                                .loyalty(20)
//                                .motherClubBonus(true)
//                                .build())
                        .role(KEEPER)
                        .behaviour(NORMAL)
                        .build()))
                .benchPlayers(emptyList())
                .penaltyTakers(emptyList())
                .matchDetail(MatchDetail.builder()
                        .tactic(Tactic.NORMAL)
                        .teamAttitude(TeamAttitude.PIN)
                        .teamSpirit(TeamSpirit.DELIRIOUS)
                        .teamSubSpirit(0.8)
                        .teamConfidence(TeamConfidence.COMPLETELY_EXAGGERATED)
                        .sideMatch(SideMatch.AWAY)
                        .styleOfPlay(0)
                        .build())
                .build();

        final Map<Integer, Lineup> lineupEvolution = this.getLineupEvolution(startingLineup);
        final Ratings ratings = Ratings.builder()
                .leftDefense(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEDEFENSE, LEFT))
                .centralDefense(this.getRatings(lineupEvolution, RatingConfigGroup.CENTRALDEFENSE, RatingZone.ALLSIDES))
                .rightDefense(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEDEFENSE, RatingZone.RIGHT))
                .midfield(this.getRatings(lineupEvolution, RatingConfigGroup.MIDFIELD, RatingZone.ALLSIDES))
                .leftAttack(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEATTACK, LEFT))
                .centralAttack(this.getRatings(lineupEvolution, RatingConfigGroup.CENTRALATTACK, RatingZone.ALLSIDES))
                .rightAttack(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEATTACK, RatingZone.RIGHT))
                .build();

        return ratings.toString();
    }

    private double getAverageRatings(final Map<Integer, Double> ratings) {

        return ratings.entrySet().stream().findFirst().get().getValue();

//        double ratingInc;
//        double totalRating90 = 0.0;
//        int duration, m_a, m_b;
//
//        final List<Integer> minutes = new ArrayList<>(ratings.keySet());
//        Collections.sort(minutes);
//
//        for (int m_i = 0; m_i < minutes.size() - 1; m_i++) {
//            m_a = minutes.get(m_i);
//            m_b = minutes.get(m_i + 1);
//            duration = m_b - m_a;
//            ratingInc = (ratings.get(m_a) + ratings.get(m_b)) / 2.0 * duration;
//
//            if (m_a < 90) {
//                totalRating90 += ratingInc;
//            }
//        }
//
//        return (int) (totalRating90 / 90.0);
    }

    private double getRatings(final Map<Integer, Lineup> lineupEvolution, final RatingConfigGroup ratingConfigGroup, final RatingZone zone) {
        return this.getAverageRatings(lineupEvolution.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entrySet -> this.getRating(entrySet.getKey(), entrySet.getValue(), ratingConfigGroup, zone))));
    }

    private Map<Integer, Lineup> getLineupEvolution(final Lineup startingLineup) {
        //return Map.of(1, startingLineup, 91, startingLineup);
        return Map.of(1, startingLineup);
    }

    private double getRating(final int minute, final Lineup lineup, final RatingConfigGroup ratingConfigGroup, final RatingZone zone) {
        final double retVal = RatingConfig.config.get(ratingConfigGroup).entrySet().stream()
                .filter(section -> section.getKey() != general)
                .mapToDouble(section -> this.getPartialRating(minute, lineup, section.getKey(), section.getValue(), zone))
                .sum();

        return this.applyCommonProps(retVal, lineup, RatingConfig.config.get(ratingConfigGroup).get(general));
    }

    private double getPartialRating(final int minute, final Lineup lineup, final RatingConfigSection section, final Map<RatingConfigProp, Double> props, final RatingZone zone) {
        final RatingZone sectionZone = section.getZone();
        final boolean useLeft = (sectionZone == THISSIDE && zone == LEFT) || (sectionZone == OTHERSIDE && zone == RIGHT) || (sectionZone == ALLSIDES);
        final boolean useMiddle = (sectionZone == MIDDLE) || (sectionZone == ALLSIDES);
        final boolean useRight = (sectionZone == THISSIDE && zone == RIGHT) || (sectionZone == OTHERSIDE && zone == LEFT) || (sectionZone == ALLSIDES);


        return this.getPlayerStrength(minute, lineup, section.getSkill(), props, useLeft, useMiddle, useRight);

    }

    private double getPlayerStrength(final int minute, final Lineup lineup, final Skill skill, final Map<RatingConfigProp, Double> props, final boolean useLeft, final boolean useMiddle, final boolean useRight) {
        return lineup.getFieldPlayers().stream()
                .filter(lineupPlayer -> props.containsKey(lineupPlayer.getRatingConfigProp()))
                .filter(lineupPlayer -> (lineupPlayer.getRole().isLeft() && useLeft) || (lineupPlayer.getRole().isMiddle() && useMiddle) || (lineupPlayer.getRole().isRight() && useRight))
                .mapToDouble(lineupPlayer -> this.calcPlayerStrength(minute, lineupPlayer.getPlayer(), skill) * props.get(lineupPlayer.getRatingConfigProp()))
                .sum();
    }

    private double calcPlayerStrength(final int minute, final Player player, final Skill skillType) {
        final Map<RatingConfigProp, Double> props = RatingConfig.config.get(PLAYERSTRENGTH).get(general);
        double form = player.getForm() + props.getOrDefault(formDelta, 0.0);
        double skill = player.getSkill(skillType) + this.getLoyaltyHomegrownBonus(player);
        final double xp = props.getOrDefault(multiXpLog10, 0.0) * Math.log10(player.getExperience());

        skill += props.getOrDefault(skillDelta, 0.0) + xp;

        form = Math.max(form, props.getOrDefault(formMin, 0.0));
        form = Math.min(form, props.getOrDefault(formMax, 99999.9));
        form *= props.getOrDefault(formMultiplier, 1.0);
        form = Math.pow(form, props.getOrDefault(formPower, 1.0));

        return skill * form;
    }

    private double getLoyaltyHomegrownBonus(final Player player) {
        return player.isMotherClubBonus()
                ? 1.5
                : (player.getLoyalty() - 1) / 19.0;
    }

    public double applyCommonProps(final double inVal, final Lineup lineup, final Map<RatingConfigProp, Double> props) {
        double retVal = inVal;
        retVal += props.getOrDefault(squareMod, 0.0) * Math.pow(retVal, 2);
        retVal += props.getOrDefault(cubeMod, 0.0) * Math.pow(retVal, 3);
        retVal *= props.getOrDefault(lineup.getMatchDetail().getTactic().getProp(), 1.0);
        final double teamSpirit = (double) lineup.getMatchDetail().getTeamSpirit().getValue() + ((double) lineup.getMatchDetail().getTeamSubSpirit());
        retVal *= Math.pow(teamSpirit * props.getOrDefault(teamSpiritPreMulti, 1 / 4.5), props.getOrDefault(teamSpiritPower, 0.0));
        retVal *= props.getOrDefault(lineup.getMatchDetail().getSideMatch().getProp(), 1.0);
        retVal *= props.getOrDefault(lineup.getMatchDetail().getTeamAttitude().getProp(), 1.0);
        retVal *= (1.0 + props.getOrDefault(confidence, 0.0) * (float) (lineup.getMatchDetail().getTeamConfidence().getValue() - 5));
        final double offensive = props.getOrDefault(trainerOff, 1.0);
        final double defensive = props.getOrDefault(trainerDef, 1.0);
        final double neutral = props.getOrDefault(trainerNeutral, 1.0);
        final double outlier = lineup.getMatchDetail().getStyleOfPlay() >= 0 ? offensive : defensive;
        retVal *= neutral + (Math.abs(lineup.getMatchDetail().getStyleOfPlay()) * 0.1) * (outlier - neutral);
        retVal *= props.getOrDefault(multiplier, 1.0);
        retVal = Math.pow(retVal, props.getOrDefault(power, 1.0));
        retVal += props.getOrDefault(delta, 0.0);
        return retVal;
    }

}
