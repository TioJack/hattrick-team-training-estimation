package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = Player.PlayerBuilder.class)
public class Player implements Serializable {

    private static final long serialVersionUID = 7365552274125573163L;

    int id;
    double form;
    double stamina;
    double keeper;
    double playmaker;
    double scorer;
    double passing;
    double winger;
    double defender;
    double setPieces;
    double experience;
    double loyalty;
    boolean motherClubBonus;
    Specialty specialty;
    int age;
    int days;
    int inclusionWeek;
    int daysForNextTraining;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PlayerBuilder {
    }

    @JsonIgnore
    public double getSkill(final Skill skill) {
        return switch (skill) {
            case GOALKEEPING -> this.keeper;
            case DEFENDING -> this.defender;
            case WINGER -> this.winger;
            case PLAY_MAKING -> this.playmaker;
            case SCORING -> this.scorer;
            case PASSING -> this.passing;
            case SET_PIECES -> this.setPieces;
        };
    }

}
