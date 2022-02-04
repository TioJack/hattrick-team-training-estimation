package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.BestFormationCriteria;
import net.ddns.tiojack.htte.model.Formation;
import net.ddns.tiojack.htte.model.FormationRating;
import net.ddns.tiojack.htte.model.Lineup;
import net.ddns.tiojack.htte.model.LineupPlayer;
import net.ddns.tiojack.htte.model.MatchDetail;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.PlayerRating;
import net.ddns.tiojack.htte.model.Ratings;
import net.ddns.tiojack.htte.model.Role;
import net.ddns.tiojack.htte.model.RoleGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FormationRatingService {

    private final PlayerRatingService playerRatingService;
    private final RatingService ratingService;

    public FormationRating getRatings(final List<Player> players, final MatchDetail matchDetail, final BestFormationCriteria bestFormationCriteria) {


        final FormationRating formationRating = Arrays.stream(Formation.values()).map(formation -> {

                    final List<PlayerRating> collect = players.stream()
                            .map(player -> this.playerRatingService.getRatings(player, matchDetail, formation))
                            .flatMap(Collection::stream)
                            .sorted(bestFormationCriteria.getPlayerRatingComparator())
                            .collect(toList());

                    List<RoleGroup> availableRoleGroups = new ArrayList<>(formation.getRoleGroups());
                    final List<PlayerRating> best11 = new ArrayList();
                    while (best11.size() < 11) {
                        final List<Integer> usedPlayers = best11.stream().map(PlayerRating::getPlayerId).collect(toList());
                        final List<Role> usedRoles = best11.stream().map(x -> x.getPosition().getRole()).collect(toList());
                        final List<PlayerRating> f = collect.stream()
                                .filter(p -> !usedPlayers.contains(p.getPlayerId())
                                        && !usedRoles.contains(p.getPosition().getRole())
                                        && availableRoleGroups.contains(p.getPosition().getRole().getRoleGroup()))
                                .collect(toList());
                        if (f.isEmpty()) {
                            break;
                        }
                        best11.add(f.get(0));
                        availableRoleGroups.remove(f.get(0).getPosition().getRole().getRoleGroup());
                        final int x = 0;
                    }

                    return FormationRating.builder()
                            .formation(formation.getName())
                            .players(best11)
                            .rating(Ratings.builder()
                                    .rightDefense(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getRightDefense()).sum())
                                    .centralDefense(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getCentralDefense()).sum())
                                    .leftDefense(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getLeftDefense()).sum())
                                    .midfield(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getMidfield()).sum())
                                    .rightAttack(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getRightAttack()).sum())
                                    .centralAttack(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getCentralAttack()).sum())
                                    .leftAttack(best11.stream().mapToDouble(playerRating -> playerRating.getRating().getLeftAttack()).sum())
                                    .build())
                            .build();

                })
                .sorted(bestFormationCriteria.getFormationRatingComparator())
                .collect(toList()).get(0);


        final Lineup lineup = Lineup.builder()
                .matchDetail(matchDetail)
                .fieldPlayers(formationRating.getPlayers().stream().map(fr -> {
                    final Player player = players.stream().filter(p -> p.getPlayerId() == fr.getPlayerId()).collect(toList()).get(0);
                    return LineupPlayer.builder()
                            .player(player)
                            .role(fr.getPosition().getRole())
                            .behaviour(fr.getPosition().getBehaviour())
                            .build();
                }).collect(toList()))
                .build();

        return FormationRating.builder()
                .formation(formationRating.getFormation())
                .players(formationRating.getPlayers())
                .rating(this.ratingService.getRatings(lineup))
                .build();
    }
}
