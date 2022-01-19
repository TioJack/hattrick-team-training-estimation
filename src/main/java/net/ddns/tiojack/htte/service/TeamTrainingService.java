package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.PlayerTrainingRQ;
import net.ddns.tiojack.htte.model.Skill;
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

@Service
@RequiredArgsConstructor
public class TeamTrainingService {

    private final PlayerTrainingService playerTrainingService;

    public TeamTrainingRS getTeamTraining(final TeamTrainingRQ teamTrainingRQ) {
        final AtomicInteger week = new AtomicInteger(0);
        // <week,trainingStageId>
        final Map<Integer, Integer> weeks = new HashMap<>();
        teamTrainingRQ.getTrainingStages().values().forEach(trainingStage ->
                IntStream.range(0, trainingStage.getDuration()).forEach(
                        stage -> weeks.put(week.incrementAndGet(), trainingStage.getTrainingStageId())
                )
        );

        final Map<Integer, List<Player>> weekPlayers = new HashMap<>();
        weeks.forEach((key, value) -> weekPlayers.put(key, this.getPlayers(key, value, weekPlayers.getOrDefault(key - 1, emptyList()), teamTrainingRQ)));

        return TeamTrainingRS.builder().weekPlayers(weekPlayers).build();
    }

    private List<Player> getPlayers(final int week, final int trainingStageId, final List<Player> previousWeekPlayers, final TeamTrainingRQ teamTrainingRQ) {
        return Stream.concat(
                        teamTrainingRQ.getPlayers().values().stream().filter(player -> player.getInclusionWeek() == week).map(player -> this.addPlayerDays(player, player.getDaysForNextTraining())),
                        previousWeekPlayers.stream().map(this::applyWeekChanges))
                .map(player -> this.applyTraining(player, this.getTrainingStage(teamTrainingRQ, trainingStageId), teamTrainingRQ.getStagePlayerTraining().get(trainingStageId).getOrDefault(player.getPlayerId(), Training.NO_TRAINING)))
                .sorted(Comparator.comparingInt(Player::getPlayerId))
                .collect(Collectors.toList());
    }

    private Player addPlayerDays(final Player player, final int addDays) {
        final int days = player.getDays() + addDays;
        return Player.builder()
                .playerId(player.getPlayerId())
                .form(player.getForm())
                .stamina(player.getStamina())
                .keeper(player.getKeeper())
                .defender(player.getDefender())
                .playmaker(player.getPlaymaker())
                .winger(player.getWinger())
                .passing(player.getPassing())
                .scorer(player.getScorer())
                .setPieces(player.getSetPieces())
                .experience(player.getExperience())
                .loyalty(player.getLoyalty())
                .motherClubBonus(player.isMotherClubBonus())
                .specialty(player.getSpecialty())
                .age(days > 111 ? player.getAge() + 1 : player.getAge())
                .days(days % 112)
                .inclusionWeek(player.getInclusionWeek())
                .daysForNextTraining(0)
                .build();
    }

    private Player applyWeekChanges(final Player player) {
        final int days = player.getDays() + 7;
        final int age = days > 111 ? player.getAge() + 1 : player.getAge();
        return Player.builder()
                .playerId(player.getPlayerId())
                .form(player.getForm())
                .stamina(player.getStamina())
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
