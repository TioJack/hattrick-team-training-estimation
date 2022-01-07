package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = TrainingRQ.TrainingRQBuilder.class)
public class TrainingRQ implements Serializable {

    private static final long serialVersionUID = 5413948346193847715L;

    double skill;
    int coach;
    int assistants;
    int intensity;
    int stamina;
    Training training;
    int age;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TrainingRQBuilder {
    }

}
