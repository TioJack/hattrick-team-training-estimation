package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = MatchDetail.MatchDetailBuilder.class)
public class MatchDetail implements Serializable {

    private static final long serialVersionUID = 2813151629603273986L;

    Tactic tactic;
    TeamAttitude teamAttitude;
    TeamSpirit teamSpirit;
    double teamSubSpirit;
    TeamConfidence teamConfidence;
    SideMatch sideMatch;
    int styleOfPlay;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MatchDetailBuilder {
    }

}
