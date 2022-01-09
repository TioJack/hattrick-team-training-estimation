package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.TeamTrainingRQ;
import net.ddns.tiojack.htte.model.TeamTrainingRS;
import net.ddns.tiojack.htte.model.Training;
import net.ddns.tiojack.htte.model.TrainingStep;
import org.springframework.stereotype.Service;

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

    public TeamTrainingRS getTeamTraining(final TeamTrainingRQ teamTrainingRQ) {
        final AtomicInteger week = new AtomicInteger(0);
        // <week,trainingStepId>
        final Map<Integer, Integer> weeks = new HashMap<>();
        teamTrainingRQ.getTrainingSteps().values().forEach(trainingStep ->
                IntStream.range(0, trainingStep.getDuration()).forEach(
                        step -> weeks.put(week.incrementAndGet(), trainingStep.getTrainingStepId())
                )
        );

        final Map<Integer, List<Player>> weekPlayers = new HashMap<>();
        weeks.forEach((key, value) -> weekPlayers.put(key, this.getPlayers(key, value, weekPlayers.getOrDefault(key - 1, emptyList()), teamTrainingRQ)));

        return TeamTrainingRS.builder().weekPlayers(weekPlayers).build();
    }

    private List<Player> getPlayers(final int week, final int trainingStepId, final List<Player> previousWeekPlayers, final TeamTrainingRQ teamTrainingRQ) {
        return Stream.concat(
                        teamTrainingRQ.getPlayers().values().stream().filter(player -> player.getInclusionWeek() == week).map(player -> this.addPlayerDays(player, player.getDaysForNextTraining())),
                        previousWeekPlayers.stream().map(player -> this.addPlayerDays(player, 7)))
                .map(player -> this.applyTraining(player, this.getTrainingStep(teamTrainingRQ, trainingStepId), teamTrainingRQ.getStepPlayerTraining().get(trainingStepId).getOrDefault(player.getPlayerId(), Training.NO_TRAINING)))
                .collect(Collectors.toList());
    }

    private Player addPlayerDays(final Player player, final int addDays) {
        final int days = player.getDays() + addDays;
        return Player.builder()
                .playerId(player.getPlayerId())
                .form(player.getForm())
                .stamina(player.getStamina())
                .keeper(player.getKeeper())
                .playmaker(player.getPlaymaker())
                .scorer(player.getScorer())
                .passing(player.getPassing())
                .winger(player.getWinger())
                .defender(player.getDefender())
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

    private TrainingStep getTrainingStep(final TeamTrainingRQ teamTrainingRQ, final int trainingStepId) {
        return teamTrainingRQ.getTrainingSteps().get(trainingStepId);
    }

    private Player applyTraining(final Player player, final TrainingStep trainingStep, final Training training) {
        return training == Training.NO_TRAINING
                ? player
                :; //TODO call PlayerTrainingService
    }
}
