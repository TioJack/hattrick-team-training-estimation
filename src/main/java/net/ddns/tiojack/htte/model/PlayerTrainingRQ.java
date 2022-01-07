package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = PlayerTrainingRQ.TrainingRQBuilder.class)
public class PlayerTrainingRQ implements Serializable {

    private static final long serialVersionUID = 5413948346193847715L;

    double skill;
    int age;
    int coach;
    int assistants;
    int intensity;
    int stamina;
    Training training;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TrainingRQBuilder {
    }

}
