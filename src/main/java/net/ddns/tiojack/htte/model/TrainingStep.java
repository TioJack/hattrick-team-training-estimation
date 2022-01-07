package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = TrainingStep.TrainingStepBuilder.class)
public class TrainingStep implements Serializable {

    private static final long serialVersionUID = 5576520405502511575L;

    int id;
    int duration;
    int coach;
    int assistants;
    int intensity;
    int stamina;
    Training training;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TrainingStepBuilder {
    }
}
