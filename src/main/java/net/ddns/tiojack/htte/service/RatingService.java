package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.Lineup;
import net.ddns.tiojack.htte.model.LineupPlayer;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.RatingConfig;
import net.ddns.tiojack.htte.model.RatingConfigGroup;
import net.ddns.tiojack.htte.model.RatingConfigProp;
import net.ddns.tiojack.htte.model.RatingConfigSection;
import net.ddns.tiojack.htte.model.RatingZone;
import net.ddns.tiojack.htte.model.Ratings;
import net.ddns.tiojack.htte.model.Role;
import net.ddns.tiojack.htte.model.Skill;
import net.ddns.tiojack.htte.model.Tactic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.ddns.tiojack.htte.model.RatingConfigGroup.PLAYERSTRENGTH;
import static net.ddns.tiojack.htte.model.RatingConfigProp.confidence;
import static net.ddns.tiojack.htte.model.RatingConfigProp.cubeMod;
import static net.ddns.tiojack.htte.model.RatingConfigProp.delta;
import static net.ddns.tiojack.htte.model.RatingConfigProp.formDelta;
import static net.ddns.tiojack.htte.model.RatingConfigProp.formMax;
import static net.ddns.tiojack.htte.model.RatingConfigProp.formMin;
import static net.ddns.tiojack.htte.model.RatingConfigProp.formMultiplier;
import static net.ddns.tiojack.htte.model.RatingConfigProp.formPower;
import static net.ddns.tiojack.htte.model.RatingConfigProp.multiXpLog10;
import static net.ddns.tiojack.htte.model.RatingConfigProp.multiplier;
import static net.ddns.tiojack.htte.model.RatingConfigProp.power;
import static net.ddns.tiojack.htte.model.RatingConfigProp.skillDelta;
import static net.ddns.tiojack.htte.model.RatingConfigProp.squareMod;
import static net.ddns.tiojack.htte.model.RatingConfigProp.teamSpiritPower;
import static net.ddns.tiojack.htte.model.RatingConfigProp.teamSpiritPreMulti;
import static net.ddns.tiojack.htte.model.RatingConfigProp.threeCdMulti;
import static net.ddns.tiojack.htte.model.RatingConfigProp.threeFwMulti;
import static net.ddns.tiojack.htte.model.RatingConfigProp.threeMfMulti;
import static net.ddns.tiojack.htte.model.RatingConfigProp.trainerDef;
import static net.ddns.tiojack.htte.model.RatingConfigProp.trainerNeutral;
import static net.ddns.tiojack.htte.model.RatingConfigProp.trainerOff;
import static net.ddns.tiojack.htte.model.RatingConfigProp.twoCdMulti;
import static net.ddns.tiojack.htte.model.RatingConfigProp.twoFwMulti;
import static net.ddns.tiojack.htte.model.RatingConfigProp.twoMfMulti;
import static net.ddns.tiojack.htte.model.RatingConfigSection.general;
import static net.ddns.tiojack.htte.model.RatingZone.ALLSIDES;
import static net.ddns.tiojack.htte.model.RatingZone.LEFT;
import static net.ddns.tiojack.htte.model.RatingZone.MIDDLE;
import static net.ddns.tiojack.htte.model.RatingZone.OTHERSIDE;
import static net.ddns.tiojack.htte.model.RatingZone.RIGHT;
import static net.ddns.tiojack.htte.model.RatingZone.THISSIDE;

@Service
@RequiredArgsConstructor
public class RatingService {

    public Ratings getRatings(final Lineup startingLineup) {
        final Map<Integer, Lineup> lineupEvolution = this.getLineupEvolution(startingLineup);
        return Ratings.builder()
                .leftDefense(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEDEFENSE, LEFT))
                .centralDefense(this.getRatings(lineupEvolution, RatingConfigGroup.CENTRALDEFENSE, ALLSIDES))
                .rightDefense(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEDEFENSE, RIGHT))
                .midfield(this.getRatings(lineupEvolution, RatingConfigGroup.MIDFIELD, ALLSIDES))
                .leftAttack(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEATTACK, LEFT))
                .centralAttack(this.getRatings(lineupEvolution, RatingConfigGroup.CENTRALATTACK, ALLSIDES))
                .rightAttack(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEATTACK, RIGHT))
                .build();
    }

    private double getAverageRatings(final Map<Integer, Double> ratings) {
        final List<Integer> minutes = ratings.keySet().stream().sorted().collect(Collectors.toList());
        return IntStream.range(0, minutes.size() - 1)
                .mapToDouble(m -> (ratings.get(minutes.get(m)) + ratings.get(minutes.get(m + 1))) / 2.0 * (minutes.get(m + 1) - minutes.get(m)))
                .sum() / 90.0;
    }

    private double getRatings(final Map<Integer, Lineup> lineupEvolution, final RatingConfigGroup ratingConfigGroup, final RatingZone zone) {
        return round(this.getAverageRatings(lineupEvolution.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entrySet -> this.getRating(entrySet.getKey(), entrySet.getValue(), ratingConfigGroup, zone)))), 2);
    }

    private static double round(final double value, final int nbDecimals) {
        final double corr = Math.pow(10.0, nbDecimals);
        return Math.round(value * corr) / corr;
    }

    private Map<Integer, Lineup> getLineupEvolution(final Lineup startingLineup) {
        return IntStream.range(0, 19).mapToObj(i -> i * 5).collect(Collectors.toMap(k -> k, v -> startingLineup));
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
        return this.getPlayersStrength(minute, lineup, section.getSkill(), props, useLeft, useMiddle, useRight);
    }

    private double getPlayersStrength(final int minute, final Lineup lineup, final Skill skill, final Map<RatingConfigProp, Double> props, final boolean useLeft, final boolean useMiddle, final boolean useRight) {
        return lineup.getFieldPlayers().stream()
                .filter(lineupPlayer -> props.containsKey(lineupPlayer.getRatingConfigProp()))
                .filter(lineupPlayer -> (lineupPlayer.getRole().isLeft() && useLeft) || (lineupPlayer.getRole().isMiddle() && useMiddle) || (lineupPlayer.getRole().isRight() && useRight))
                .mapToDouble(lineupPlayer -> this.getPlayerStrength(minute, lineup, lineupPlayer, skill, props))
                .sum();
    }

    private double getPlayerStrength(final int minute, final Lineup lineup, final LineupPlayer lineupPlayer, final Skill skill, final Map<RatingConfigProp, Double> props) {
        double retVal;
        retVal = this.getPlayerSkillStrength(lineupPlayer.getPlayer(), skill);
        retVal *= props.get(lineupPlayer.getRatingConfigProp());
        retVal *= this.getStaminaEffect(lineupPlayer.getPlayer().getStamina(), minute, 0, lineup.getMatchDetail().getTactic() == Tactic.PRESSING);
        retVal *= this.adjustForCrowding(lineup, lineupPlayer.getRole());
        return retVal;
    }

    private double getPlayerSkillStrength(final Player player, final Skill skillType) {
        final Map<RatingConfigProp, Double> props = RatingConfig.config.get(PLAYERSTRENGTH).get(general);
        double form = player.getForm() + props.getOrDefault(formDelta, 0.0);
        double skill = player.getSkill(skillType) + this.getLoyaltyHomegrownBonus(player);
        final double xp = props.getOrDefault(multiXpLog10, 0.0) * Math.log10(player.getExperience());
        skill += props.getOrDefault(skillDelta, 0.0) + xp;
        form = Math.max(form, props.getOrDefault(formMin, 0.0));
        form = Math.min(form, props.getOrDefault(formMax, 99999.0));
        form *= props.getOrDefault(formMultiplier, 1.0);
        form = Math.pow(form, props.getOrDefault(formPower, 1.0));
        return skill * form;
    }

    private double getLoyaltyHomegrownBonus(final Player player) {
        return player.isMotherClubBonus() ? 1.5 : (player.getLoyalty() - 1) / 19.0;
    }

    private double getStaminaEffect(double stamina, final double t, final double tEnter, final boolean isTacticPressing) {
        stamina -= 1;
        final double P = isTacticPressing ? 1.1 : 1.0;
        final double energyLossPerMinute;
        double energy;
        if (stamina >= 7) {
            energyLossPerMinute = -3.25 * P / 5;
            energy = 125 + (stamina - 7) * 100 / 7.0 - energyLossPerMinute;  //energy when entering the field for player whose stamina >= 8
        } else {
            energyLossPerMinute = -P * (5.95 - 27 * stamina / 70.0) / 5;
            energy = 102 + 23 / 7.0 * stamina - energyLossPerMinute; //energy when entering the field for player whose stamina < 8
        }
        if (t > 45d && tEnter < 45d) {
            energy += 18.75;  // Energy recovery during half-time
        }
        energy += energyLossPerMinute * (t - tEnter);
        return Math.max(10, Math.min(100, energy)) / 100.0;
    }

    private double adjustForCrowding(final Lineup lineup, final Role role) {
        final Map<RatingConfigProp, Double> props = RatingConfig.config.get(PLAYERSTRENGTH).get(general);
        double weight = 1;
        switch (role.getAbr()) {
            case "CD":
                if (lineup.getNumberCentralDefender() == 2) {
                    weight = props.get(twoCdMulti);
                } else if (lineup.getNumberCentralDefender() == 3) {
                    weight = props.get(threeCdMulti);
                }
                break;
            case "IM":
                if (lineup.getNumberInnerMidfield() == 2) {
                    weight = props.get(twoMfMulti);
                } else if (lineup.getNumberInnerMidfield() == 3) {
                    weight = props.get(threeMfMulti);
                }
                break;
            case "FW":
                if (lineup.getNumberForward() == 2) {
                    weight = props.get(twoFwMulti);
                } else if (lineup.getNumberForward() == 3) {
                    weight = props.get(threeFwMulti);
                }
                break;
        }
        return weight;
    }

    private double applyCommonProps(final double inVal, final Lineup lineup, final Map<RatingConfigProp, Double> props) {
        double retVal = inVal;
        retVal += props.getOrDefault(squareMod, 0.0) * Math.pow(retVal, 2);
        retVal += props.getOrDefault(cubeMod, 0.0) * Math.pow(retVal, 3);
        retVal *= props.getOrDefault(lineup.getMatchDetail().getTactic().getProp(), 1.0);
        final double teamSpirit = lineup.getMatchDetail().getTeamSpirit().getValue() + lineup.getMatchDetail().getTeamSubSpirit();
        retVal *= Math.pow(teamSpirit * props.getOrDefault(teamSpiritPreMulti, 1 / 4.5), props.getOrDefault(teamSpiritPower, 0.0));
        retVal *= props.getOrDefault(lineup.getMatchDetail().getSideMatch().getProp(), 1.0);
        retVal *= props.getOrDefault(lineup.getMatchDetail().getTeamAttitude().getProp(), 1.0);
        retVal *= (1.0 + props.getOrDefault(confidence, 0.0) * (lineup.getMatchDetail().getTeamConfidence().getValue() - 5));
        final double offensive = props.getOrDefault(trainerOff, 1.0);
        final double defensive = props.getOrDefault(trainerDef, 1.0);
        final double neutral = props.getOrDefault(trainerNeutral, 1.0);
        final double outlier = lineup.getMatchDetail().getStyleOfPlay() >= 0 ? offensive : defensive;
        retVal *= neutral + (Math.abs(lineup.getMatchDetail().getStyleOfPlay()) * 0.01) * (outlier - neutral);
        retVal *= props.getOrDefault(multiplier, 1.0);
        retVal = Math.pow(retVal, props.getOrDefault(power, 1.0));
        retVal += props.getOrDefault(delta, 0.0);
        return retVal;
    }

}
