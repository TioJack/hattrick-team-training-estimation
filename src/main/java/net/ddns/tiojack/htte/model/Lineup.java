package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Builder
@Value
@JsonDeserialize(builder = Lineup.LineupBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lineup implements Serializable {

    private static final long serialVersionUID = 5618198214082859961L;

    List<LineupPlayer> fieldPlayers;
    List<LineupPlayer> benchPlayers;
    //List<Substitution> Substitutions;
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

    @JsonIgnore
    public int getNumberCentralDefender() {
        return (int) this.fieldPlayers.stream()
                .filter(lineupPlayer -> lineupPlayer.getRole().getAbr().equals("CD"))
                .count();
    }

    @JsonIgnore
    public int getNumberInnerMidfield() {
        return (int) this.fieldPlayers.stream()
                .filter(lineupPlayer -> lineupPlayer.getRole().getAbr().equals("IM"))
                .count();
    }

    @JsonIgnore
    public int getNumberForward() {
        return (int) this.fieldPlayers.stream()
                .filter(lineupPlayer -> lineupPlayer.getRole().getAbr().equals("FW"))
                .count();
    }

}
