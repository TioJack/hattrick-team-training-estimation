package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
@Value
@JsonDeserialize(builder = TeamTrainingRQ.TeamTrainingRQBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamTrainingRQ implements Serializable {

    private static final long serialVersionUID = 5331342158872356541L;

    List<TrainingStep> trainingSteps;
    //<playerId,Player>
    Map<Integer, Player> players;
    //<trainingStepId,<playerId,Training>>
    Map<Integer, Map<Integer, Training>> stepPlayerTraining;

    private TeamTrainingRQ(final TeamTrainingRQBuilder builder) {
        this.trainingSteps = Collections.unmodifiableList(builder.trainingSteps);
        this.players = Collections.unmodifiableMap(builder.players);
        this.stepPlayerTraining = Collections.unmodifiableMap(builder.stepPlayerTraining);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class TeamTrainingRQBuilder {
        public TeamTrainingRQ build() {
            return new TeamTrainingRQ(this);
        }
    }
}
