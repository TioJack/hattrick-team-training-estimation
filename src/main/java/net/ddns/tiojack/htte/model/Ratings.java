package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Value
@JsonDeserialize(builder = Ratings.RatingsBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ratings implements Serializable {

    @Serial
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

    @Override
    public String toString() {
        return Stream.of(this.leftDefense, this.centralDefense, this.rightDefense, this.midfield, this.leftAttack, this.centralAttack, this.rightAttack)
                .map(Object::toString)
                .collect(Collectors.joining(" | "));
    }
}
