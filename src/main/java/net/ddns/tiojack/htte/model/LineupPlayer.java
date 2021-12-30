package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Value
@JsonDeserialize(builder = LineupPlayer.LineupPlayerBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LineupPlayer implements Serializable {

    @Serial
    private static final long serialVersionUID = 7365552274125573163L;

    Player player;
    Role role;
    Behaviour behaviour;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LineupPlayerBuilder {
    }

    public RatingConfigProp getRatingConfigProp() {
        return RatingConfigProp.valueOf(Stream.of(this.role.getAbr(), this.behaviour.getAbr(), this.player.getSpecialty() == Specialty.TECHNICAL ? "tech" : null)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("_")));
    }

}
