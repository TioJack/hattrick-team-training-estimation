package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = Player.LineupPlayerBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 7365552274125573163L;

    int id;
    int form;
    double stamina;
    double keeper;
    double playmaker;
    double scorer;
    double passing;
    double winger;
    double defender;
    double setPieces;
    double experience;
    int loyalty;
    boolean motherClubBonus;
    Specialty specialty;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LineupPlayerBuilder {
    }

    public double getSkill(final SkillType skillType) {
        return switch (skillType) {
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
