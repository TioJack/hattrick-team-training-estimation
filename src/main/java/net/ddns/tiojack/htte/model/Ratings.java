package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = Ratings.RatingsBuilder.class)
public class Ratings implements Serializable {

    private static final long serialVersionUID = 5039386724348503397L;

    double leftDefense;
    double centralDefense;
    double rightDefense;
    double midfield;
    double leftAttack;
    double centralAttack;
    double rightAttack;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RatingsBuilder {
    }

}
