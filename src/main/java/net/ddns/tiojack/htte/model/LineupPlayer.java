package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Value
@JsonDeserialize(builder = LineupPlayer.LineupPlayerBuilder.class)
public class LineupPlayer implements Serializable {

    private static final long serialVersionUID = 7365552274125573163L;

    Player player;
    Role role;
    Behaviour behaviour;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LineupPlayerBuilder {
    }

    @JsonIgnore
    public RatingConfigProp getRatingConfigProp() {
        return RatingConfigProp.valueOf(Stream.of(this.role.getRoleGroup().getAbr(), this.behaviour.getAbr(), this.role.getRoleGroup() == RoleGroup.FORWARD && this.player.getSpecialty() == Specialty.TECHNICAL ? "tech" : null)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("_")));
    }

}
