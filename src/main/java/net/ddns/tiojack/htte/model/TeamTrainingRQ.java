package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

@Builder
@Value
@JsonDeserialize(builder = TeamTrainingRQ.TeamTrainingRQBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamTrainingRQ implements Serializable {

    private static final long serialVersionUID = 5331342158872356541L;

    //<playerId,Player>
    Map<Integer, Player> players;

    //<trainingStageId,TrainingStage>
    Map<Integer, TrainingStage> trainingStages;

    //<trainingStageId,<playerId,Training>>
    Map<Integer, Map<Integer, Training>> stagePlayerTraining;

    private TeamTrainingRQ(final TeamTrainingRQBuilder builder) {
        this.players = Collections.unmodifiableMap(builder.players);
        this.trainingStages = Collections.unmodifiableMap(builder.trainingStages);
        this.stagePlayerTraining = Collections.unmodifiableMap(builder.stagePlayerTraining);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class TeamTrainingRQBuilder {
        public TeamTrainingRQ build() {
            return new TeamTrainingRQ(this);
        }
    }
}
