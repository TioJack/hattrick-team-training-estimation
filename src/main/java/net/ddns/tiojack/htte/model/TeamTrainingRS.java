package net.ddns.tiojack.htte.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@Value
@Jacksonized
public class TeamTrainingRS implements Serializable {

    private static final long serialVersionUID = 7486483872996685721L;

    // <week,players>
    Map<Integer, List<Player>> weekPlayers;

    // <week,rating>
    Map<Integer, FormationRating> weekRatings;

    int bestWeek;
    FormationRating bestRating;

}
