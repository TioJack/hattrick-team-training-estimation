package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = TrainingStage.TrainingStageBuilder.class)
public class TrainingStage implements Serializable {

    private static final long serialVersionUID = 5576520405502511575L;

    int trainingStageId;
    int duration;
    int coach;
    int assistants;
    int intensity;
    int stamina;
    Training training;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TrainingStageBuilder {
    }
}
