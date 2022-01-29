package net.ddns.tiojack.htte.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.LineMatch;
import net.ddns.tiojack.htte.model.MatchDetail;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.Position;
import net.ddns.tiojack.htte.model.Ratings;
import net.ddns.tiojack.htte.model.Skill;
import net.ddns.tiojack.htte.model.ZoneMatch;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerRatingService {

    public Map<Position, Ratings> getRatings(final Player player, final MatchDetail matchDetail) {
        return Arrays.stream(Position.values()).collect(Collectors.toMap(position -> position, position -> Ratings.builder()
                .rightDefense(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.RIGHT_DEFENSE, matchDetail))
                .centralDefense(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.CENTRAL_DEFENSE, matchDetail))
                .leftDefense(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.LEFT_DEFENSE, matchDetail))
                .midfield(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.MIDFIELD, matchDetail))
                .rightAttack(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.RIGHT_ATTACK, matchDetail))
                .centralAttack(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.CENTRAL_ATTACK, matchDetail))
                .leftAttack(this.getCoefficientByPositionAndZone(player, position, ZoneMatch.LEFT_ATTACK, matchDetail))
                .build()));
    }

    private static final Map<ZoneMatch, Map<Position, Map<Skill, Double>>> config = ImmutableMap.<ZoneMatch, Map<Position, Map<Skill, Double>>>builder()
            .put(ZoneMatch.RIGHT_DEFENSE, ImmutableMap.<Position, Map<Skill, Double>>builder()
                    .put(Position.KP, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.GOALKEEPING, 0.15555)
                            .put(Skill.DEFENDING, 0.06375)
                            .build())
                    .put(Position.MCD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.1326 * 0.5)
                            .build())
                    .put(Position.MCDO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.10200000000000001 * 0.5)
                            .build())
                    .put(Position.RCD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.1326)
                            .build())
                    .put(Position.RCDO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.10200000000000001)
                            .build())
                    .put(Position.RCDTW, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.20655)
                            .build())
                    .put(Position.RWB, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.2346)
                            .build())
                    .put(Position.RWBO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.1887)
                            .build())
                    .put(Position.RWBTM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.19125)
                            .build())
                    .put(Position.RWBD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.255)
                            .build())
                    .put(Position.MIM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.04845 * 0.5)
                            .build())
                    .put(Position.MIMO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.022949999999999998 * 0.5)
                            .build())
                    .put(Position.MIMD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.06885000000000001 * 0.5)
                            .build())
                    .put(Position.RIM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.04845)
                            .build())
                    .put(Position.RIMO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.022949999999999998)
                            .build())
                    .put(Position.RIMD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.06885000000000001)
                            .build())
                    .put(Position.RIMTW, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.0612)
                            .build())
                    .put(Position.RW, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.08925)
                            .build())
                    .put(Position.RWO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.056100000000000004)
                            .build())
                    .put(Position.RWD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.15555)
                            .build())
                    .put(Position.RWTM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, 0.07395)
                            .build())
                    .build())
            .put(ZoneMatch.CENTRAL_DEFENSE, ImmutableMap.<Position, Map<Skill, Double>>builder()
                    .put(Position.KP, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.GOALKEEPING, 0.135333285)
                            .put(Skill.DEFENDING, 0.054444425000000005)
                            .build())
                    .put(Position.MCD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.MCDO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RCD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RCDO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RCDTW, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LCD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LCDO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LCDTW, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RWB, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RWBO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RWBTM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.RWBD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LWB, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LWBO, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LWBTM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.LWBD, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.MIM, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .put(Position.X, ImmutableMap.<Skill, Double>builder()
                            .put(Skill.DEFENDING, X)
                            .build())
                    .build())

            .build();

    private double getCoefficientByPositionAndZone(final Player player, final Position position, final ZoneMatch zone, final MatchDetail matchDetail) {
        double c;
        try {
            c = config.get(zone).get(position).entrySet().stream().mapToDouble(e -> this.getEffectiveSkill(player, e.getKey()) * e.getValue()).sum();
        } catch (final Exception e) {
            c = 0;
        }
        return this.applyCommonProps(c, zone, matchDetail);
    }

    private double applyCommonProps(final double inVal, final ZoneMatch zone, final MatchDetail matchDetail) {
        double retVal = inVal;
        //retVal += props.getOrDefault(squareMod, 0.0) * Math.pow(retVal, 2);
        //retVal += props.getOrDefault(cubeMod, 0.0) * Math.pow(retVal, 3);
        //retVal *= props.getOrDefault(matchDetail.getTactic().getProp(), 1.0);
        if (zone == ZoneMatch.MIDFIELD) {
            final double teamSpirit = matchDetail.getTeamSpirit().getValue() + matchDetail.getTeamSubSpirit();
            retVal *= Math.pow(teamSpirit * 0.147832, 0.417779);
            retVal *= matchDetail.getSideMatch().getFactor();
            retVal *= matchDetail.getTeamAttitude().getFactor();
        }
        if (zone.getLine() == LineMatch.ATTACK) {
            retVal *= (1.0 + 0.0525 * (matchDetail.getTeamConfidence().getValue() - 5));
        }

        final double offensive = zone.getLine().getTrainerOffensiveFactor();
        final double defensive = zone.getLine().getTrainerDefensiveFactor();
        final double outlier = matchDetail.getStyleOfPlay() >= 0 ? offensive : defensive;
        retVal *= 1.0 + (Math.abs(matchDetail.getStyleOfPlay()) * 0.01) * (outlier - 1.0);

        retVal = Math.pow(retVal, 1.165);
        retVal += 0.75;
        return retVal;
    }

    private double getEffectiveSkill(final Player player, final Skill skill) {
        double form = player.getForm();
        double value = player.getSkill(skill) + this.getLoyaltyHomegrownBonus(player);
        final double xp = (4.0 / 3.0) * Math.log10(player.getExperience());
        value += xp;
        form = Math.max(form, 1.0);
        form = Math.min(form, 8.0);
        form *= 0.125;
        form = Math.pow(form, 2.0 / 3.0);
        return value * form;
    }

    private double getLoyaltyHomegrownBonus(final Player player) {
        return player.isMotherClubBonus() ? 1.5 : (player.getLoyalty() - 1) / 19.0;
    }

}
