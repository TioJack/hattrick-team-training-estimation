package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.MatchDetail;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.PlayerTrainingRQ;
import net.ddns.tiojack.htte.model.Ratings;
import net.ddns.tiojack.htte.model.SideMatch;
import net.ddns.tiojack.htte.model.Skill;
import net.ddns.tiojack.htte.model.Tactic;
import net.ddns.tiojack.htte.model.TeamAttitude;
import net.ddns.tiojack.htte.model.TeamTrainingRQ;
import net.ddns.tiojack.htte.model.TeamTrainingRS;
import net.ddns.tiojack.htte.model.Training;
import net.ddns.tiojack.htte.model.TrainingStage;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static net.ddns.tiojack.htte.model.TeamConfidence.WONDERFUL;
import static net.ddns.tiojack.htte.model.TeamSpirit.WALKING_ON_CLOUDS;

@Service
@RequiredArgsConstructor
public class TeamTrainingService {

    private final PlayerTrainingService playerTrainingService;
    private final PlayerRatingService playerRatingService;

    public TeamTrainingRS getTeamTraining(final TeamTrainingRQ teamTrainingRQ) {
        final AtomicInteger week = new AtomicInteger(0);
        // <week,trainingStageId>
        final Map<Integer, Integer> weeks = new HashMap<>();
        teamTrainingRQ.getTrainingStages().values().forEach(trainingStage ->
                IntStream.range(0, trainingStage.getDuration()).forEach(
                        stage -> weeks.put(week.incrementAndGet(), trainingStage.getTrainingStageId())
                )
        );

        final MatchDetail matchDetail = MatchDetail.builder()
                .sideMatch(SideMatch.HOME)
                .teamConfidence(WONDERFUL)
                .styleOfPlay(0)
                .tactic(Tactic.NORMAL)
                .teamAttitude(TeamAttitude.PIN)
                .teamSpirit(WALKING_ON_CLOUDS)
                .teamSubSpirit(0.0)
                .build();

        final Map<Integer, List<Player>> weekPlayers = new HashMap<>();
        weeks.forEach((key, value) -> {
            final List<Player> players = this.getPlayers(key, value, weekPlayers.getOrDefault(key - 1, emptyList()), teamTrainingRQ, matchDetail);
            weekPlayers.put(key, players);

            final List<Ratings> collect = players.stream().flatMap(player -> player.getRatings().values().stream()).sorted(Comparator.comparingDouble(Ratings::getMidfield).reversed()).collect(Collectors.toList());

            final int f = 1;
        });

        return TeamTrainingRS.builder()
                .weekPlayers(weekPlayers)
                .build();
    }

    private List<Player> getPlayers(final int week, final int trainingStageId, final List<Player> previousWeekPlayers, final TeamTrainingRQ teamTrainingRQ, final MatchDetail matchDetail) {
        final TrainingStage trainingStage = this.getTrainingStage(teamTrainingRQ, trainingStageId);
        return Stream.concat(
                        teamTrainingRQ.getPlayers().values().stream().filter(player -> player.getInclusionWeek() == week).map(player -> this.applyWeekChanges(player, player.getDaysForNextTraining(), trainingStage)),
                        previousWeekPlayers.stream().map(player -> this.applyWeekChanges(player, 7, trainingStage)))
                .map(player -> this.applyTraining(player, trainingStage, teamTrainingRQ.getStagePlayerTraining().get(trainingStageId).getOrDefault(player.getPlayerId(), Training.NO_TRAINING)))
                .map(player -> this.calcRating(player, matchDetail))
                .sorted(Comparator.comparingInt(Player::getPlayerId))
                .collect(Collectors.toList());
    }

    private Player calcRating(final Player player, final MatchDetail matchDetail) {
        player.setRatings(this.playerRatingService.getRatings(player, matchDetail));
        return player;
    }

    private Player applyWeekChanges(final Player player, final int addDays, final TrainingStage trainingStage) {
        final int days = player.getDays() + addDays;
        final int age = days > 111 ? player.getAge() + 1 : player.getAge();
        return Player.builder()
                .playerId(player.getPlayerId())
                .form(player.getForm())
                .stamina(this.playerTrainingService.getStaminaTraining(age, player.getStamina(), trainingStage.getStamina(), trainingStage.getIntensity()))
                .keeper(Math.max(0, player.getKeeper() - this.playerTrainingService.getDropAge(Skill.GOALKEEPING, age)))
                .defender(Math.max(0, player.getDefender() - this.playerTrainingService.getDropAge(Skill.DEFENDING, age)))
                .playmaker(Math.max(0, player.getPlaymaker() - this.playerTrainingService.getDropAge(Skill.PLAY_MAKING, age)))
                .winger(Math.max(0, player.getWinger() - this.playerTrainingService.getDropAge(Skill.WINGER, age)))
                .passing(Math.max(0, player.getPassing() - this.playerTrainingService.getDropAge(Skill.PASSING, age)))
                .scorer(Math.max(0, player.getScorer() - this.playerTrainingService.getDropAge(Skill.SCORING, age)))
                .setPieces(Math.max(0, player.getSetPieces() - this.playerTrainingService.getDropAge(Skill.SET_PIECES, age)))
                .experience(player.getExperience())
                .loyalty(player.getLoyalty())
                .motherClubBonus(player.isMotherClubBonus())
                .specialty(player.getSpecialty())
                .age(age)
                .days(days % 112)
                .inclusionWeek(player.getInclusionWeek())
                .daysForNextTraining(0)
                .build();
    }

    private TrainingStage getTrainingStage(final TeamTrainingRQ teamTrainingRQ, final int trainingStageId) {
        return teamTrainingRQ.getTrainingStages().get(trainingStageId);
    }

    private Player applyTraining(final Player player, final TrainingStage trainingStage, final Training training) {
        return training == Training.NO_TRAINING
                ? player
                : this.applyPlayerTraining(player, trainingStage, training);
    }

    private Player applyPlayerTraining(final Player player, final TrainingStage trainingStage, final Training training) {
        final PlayerTrainingRQ playerTrainingRQ = PlayerTrainingRQ.builder()
                .skill(player.getSkill(training.getSkill()))
                .age(player.getAge())
                .coach(trainingStage.getCoach())
                .assistants(trainingStage.getAssistants())
                .intensity(trainingStage.getIntensity())
                .stamina(trainingStage.getStamina())
                .training(training)
                .build();
        return this.addPlayerTraining(player, training.getSkill(), this.playerTrainingService.getPlayerTraining(playerTrainingRQ));
    }


    private Player addPlayerTraining(final Player player, final Skill skill, final double addValueTraining) {
        double keeper = player.getKeeper();
        double defender = player.getDefender();
        double playmaker = player.getPlaymaker();
        double winger = player.getWinger();
        double passing = player.getPassing();
        double scorer = player.getScorer();
        double setPieces = player.getSetPieces();

        switch (skill) {
            case GOALKEEPING:
                keeper += addValueTraining;
                break;
            case DEFENDING:
                defender += addValueTraining;
                break;
            case PLAY_MAKING:
                playmaker += addValueTraining;
                break;
            case WINGER:
                winger += addValueTraining;
                break;
            case PASSING:
                passing += addValueTraining;
                break;
            case SCORING:
                scorer += addValueTraining;
                break;
            case SET_PIECES:
                setPieces += addValueTraining;
                break;
        }

        return Player.builder()
                .playerId(player.getPlayerId())
                .form(player.getForm())
                .stamina(player.getStamina())
                .keeper(keeper)
                .defender(defender)
                .playmaker(playmaker)
                .winger(winger)
                .passing(passing)
                .scorer(scorer)
                .setPieces(setPieces)
                .experience(player.getExperience())
                .loyalty(player.getLoyalty())
                .motherClubBonus(player.isMotherClubBonus())
                .specialty(player.getSpecialty())
                .age(player.getAge())
                .days(player.getDays())
                .inclusionWeek(player.getInclusionWeek())
                .daysForNextTraining(player.getDaysForNextTraining())
                .build();
    }

}
