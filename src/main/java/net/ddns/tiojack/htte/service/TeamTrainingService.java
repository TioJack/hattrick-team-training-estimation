package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.Player;
import net.ddns.tiojack.htte.model.TeamTrainingRQ;
import net.ddns.tiojack.htte.model.TeamTrainingRS;
import net.ddns.tiojack.htte.model.Training;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;

@Service
@RequiredArgsConstructor
public class TeamTrainingService {

    public TeamTrainingRS getTeamTraining(final TeamTrainingRQ teamTrainingRQ) {
        final AtomicInteger week = new AtomicInteger(0);
        // <week,trainingStepId>
        final Map<Integer, Integer> weeks = new HashMap<>();
        teamTrainingRQ.getTrainingSteps().forEach(trainingStep ->
                IntStream.range(0, trainingStep.getDuration()).forEach(
                        step -> weeks.put(week.incrementAndGet(), trainingStep.getTrainingStepId())
                )
        );

        final Map<Integer, Map<Integer, Player>> weekPlayers = new HashMap<>();
        weeks.entrySet().forEach(
                entry -> weekPlayers.put(entry.getKey(), this.getPlayers(entry.getKey(), entry.getValue(), weekPlayers.getOrDefault(entry.getKey() - 1, emptyMap()), teamTrainingRQ))
        );

        return TeamTrainingRS.builder()
                .weekPlayers(weekPlayers)
                .build();
    }

    private Map<Integer, Player> getPlayers(final int week, final int trainingStepId, final Map<Integer, Player> previousWeekPlayers, final TeamTrainingRQ teamTrainingRQ) {
        final List<Player> players = Stream.concat(
                teamTrainingRQ.getPlayers().values().stream().filter(player -> player.getInclusionWeek() == week),
                previousWeekPlayers.values().stream()).collect(Collectors.toList());

        //TODO use players to update information
        //TODO not use Map<Integer, Player> ==> Use List<Player>
        return teamTrainingRQ.getStepPlayerTraining().get(trainingStepId).entrySet().stream()
                .map(e -> this.applyTraining(e.getKey(), e.getValue(), previousWeekPlayers, teamTrainingRQ))
                .collect(Collectors.toMap(Player::getPlayerId, player -> player));
    }

    private Player getPreviousWeekPlayer(final Integer playerId, final Map<Integer, Player> previousWeekPlayers, final Map<Integer, Player> initPlayers) {


        return null;
    }

    private Player applyTraining(final Integer playerId, final Training training, final Map<Integer, Player> previousWeekPlayers, final TeamTrainingRQ teamTrainingRQ) {


        return null;
    }

}
