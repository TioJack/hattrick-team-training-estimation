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
import net.ddns.tiojack.htte.model.SkillType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static net.ddns.tiojack.htte.model.Behaviour.NORMAL;
import static net.ddns.tiojack.htte.model.RatingConfigSection.general;
import static net.ddns.tiojack.htte.model.RatingZone.ALLSIDES;
import static net.ddns.tiojack.htte.model.RatingZone.LEFT;
import static net.ddns.tiojack.htte.model.RatingZone.MIDDLE;
import static net.ddns.tiojack.htte.model.RatingZone.OTHERSIDE;
import static net.ddns.tiojack.htte.model.RatingZone.RIGHT;
import static net.ddns.tiojack.htte.model.RatingZone.THISSIDE;
import static net.ddns.tiojack.htte.model.Role.KEEPER;

@Service
@RequiredArgsConstructor
public class RatingService {

    public String getRatings() {

        final Lineup startingLineup = Lineup.builder()
                .fieldPlayers(Collections.singletonList(LineupPlayer.builder()
                        .player(Player.builder()
                                .id(1)
                                .form(6)
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
                        .role(KEEPER)
                        .behaviour(NORMAL)
                        .build()))
                .benchPlayers(emptyList())
                .penaltyTakers(emptyList())
                .build();

        final Map<Integer, Lineup> lineupEvolution = this.getLineupEvolution(startingLineup);
        final Ratings ratings = Ratings.builder()
                .leftDefense(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEDEFENSE, LEFT))
//                .centralDefense(this.getRatings(lineupEvolution, RatingConfigGroup.CENTRALDEFENSE, RatingZone.ALLSIDES))
//                .rightDefense(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEDEFENSE, RatingZone.RIGHT))
//                .midfield(this.getRatings(lineupEvolution, RatingConfigGroup.MIDFIELD, RatingZone.ALLSIDES))
//                .leftAttack(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEATTACK, LEFT))
//                .centralAttack(this.getRatings(lineupEvolution, RatingConfigGroup.CENTRALATTACK, RatingZone.ALLSIDES))
//                .rightAttack(this.getRatings(lineupEvolution, RatingConfigGroup.SIDEATTACK, RatingZone.RIGHT))
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

        return retVal;
        //return applyCommonProps(retVal, params, RatingPredictionParameter.GENERAL);
    }

    private double getPartialRating(final int minute, final Lineup lineup, final RatingConfigSection section, final Map<RatingConfigProp, Double> props, final RatingZone zone) {
        final RatingZone sectionZone = section.getZone();
        final boolean useLeft = (sectionZone == THISSIDE && zone == LEFT) || (sectionZone == OTHERSIDE && zone == RIGHT) || (sectionZone == ALLSIDES);
        final boolean useMiddle = (sectionZone == MIDDLE) || (sectionZone == ALLSIDES);
        final boolean useRight = (sectionZone == THISSIDE && zone == RIGHT) || (sectionZone == OTHERSIDE && zone == LEFT) || (sectionZone == ALLSIDES);


        return this.getPlayerStrength(minute, lineup, section.getSkillType(), props, useLeft, useMiddle, useRight);

    }

    private double getPlayerStrength(final int minute, final Lineup lineup, final SkillType skillType, final Map<RatingConfigProp, Double> props, final boolean useLeft, final boolean useMiddle, final boolean useRight) {
        return lineup.getFieldPlayers().stream()
                .filter(lineupPlayer -> props.containsKey(lineupPlayer.getRatingConfigProp()))
                .filter(lineupPlayer -> (lineupPlayer.getRole().isLeft() && useLeft) || (lineupPlayer.getRole().isMiddle() && useMiddle) || (lineupPlayer.getRole().isRight() && useRight))
                .mapToDouble(lineupPlayer -> this.calcPlayerStrength(minute, lineupPlayer.getPlayer(), skillType))
                .sum();
    }

    private double calcPlayerStrength(final int minute, final Player player, final SkillType skillType) {
        final double skill = player.getSkill(skillType);
        return skill; //+ this.getLoyaltyHomegrownBonus(player);
    }

    private double getLoyaltyHomegrownBonus(final Player player) {
        return player.isMotherClubBonus()
                ? 1.5
                : (player.getLoyalty() - 1) / 19.0;
    }

}
