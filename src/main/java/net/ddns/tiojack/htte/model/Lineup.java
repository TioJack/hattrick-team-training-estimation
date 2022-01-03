package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Builder
@Value
@JsonDeserialize(builder = Lineup.LineupBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lineup implements Serializable {

    @Serial
    private static final long serialVersionUID = 5618198214082859961L;

    List<LineupPlayer> fieldPlayers;
    List<LineupPlayer> benchPlayers;
    //List<Substitution> Substitution;
    List<LineupPlayer> penaltyTakers;
    MatchDetail matchDetail;

    private Lineup(final Lineup.LineupBuilder builder) {
        this.fieldPlayers = Collections.unmodifiableList(builder.fieldPlayers);
        this.benchPlayers = Collections.unmodifiableList(builder.benchPlayers);
        this.penaltyTakers = Collections.unmodifiableList(builder.penaltyTakers);
        this.matchDetail = builder.matchDetail;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class LineupBuilder {
        public Lineup build() {
            return new Lineup(this);
        }
    }

}
