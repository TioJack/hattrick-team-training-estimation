package net.ddns.tiojack.htte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Builder
@Value
@Jacksonized
public class Player implements Serializable {

    private static final long serialVersionUID = 7365552274125573163L;

    int playerId;
    double form;
    double stamina;
    double keeper;
    double defender;
    double playmaker;
    double winger;
    double passing;
    double scorer;
    double setPieces;
    double experience;
    double leadership;
    double loyalty;
    boolean motherClubBonus;
    Specialty specialty;
    int age;
    int days;
    int inclusionWeek;
    int daysForNextTraining;

    @JsonIgnore
    public double getSkill(final Skill skill) {
        switch (skill) {
            case GOALKEEPING:
                return this.keeper;
            case DEFENDING:
                return this.defender;
            case WINGER:
                return this.winger;
            case PLAY_MAKING:
                return this.playmaker;
            case SCORING:
                return this.scorer;
            case PASSING:
                return this.passing;
            case SET_PIECES:
                return this.setPieces;
        }
        return this.setPieces;
    }

}
