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
@JsonDeserialize(builder = TeamTrainingRS.TeamTrainingRSBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamTrainingRS implements Serializable {

    private static final long serialVersionUID = 7486483872996685721L;

    // <week,players>
    Map<Integer, List<Player>> weekPlayers;

    private TeamTrainingRS(final TeamTrainingRSBuilder builder) {
        this.weekPlayers = Collections.unmodifiableMap(builder.weekPlayers);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class TeamTrainingRSBuilder {
        public TeamTrainingRS build() {
            return new TeamTrainingRS(this);
        }
    }
}
