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

    public int getHatStats() {
        return HTfloat2int(this.leftDefense) + HTfloat2int(this.centralDefense) + HTfloat2int(this.rightDefense) + HTfloat2int(this.midfield) * 3 + HTfloat2int(this.leftAttack) + HTfloat2int(this.centralAttack) + HTfloat2int(this.rightAttack);
    }

    public int getDefense() {
        return HTfloat2int(this.leftDefense) + HTfloat2int(this.centralDefense) + HTfloat2int(this.rightDefense);
    }

    public int getMidfield3() {
        return HTfloat2int(this.midfield) * 3;
    }

    public int getAttack() {
        return HTfloat2int(this.leftAttack) + HTfloat2int(this.centralAttack) + HTfloat2int(this.rightAttack);
    }

    private static int HTfloat2int(final double x) {
        return (int) (((x - 1.0f) * 4.0f) + 1.0f);
    }

    public double getPeasoStats() {
        return 0.46 * HTfloat2int(this.midfield) +
                0.32 * (0.3 * (HTfloat2int(this.leftAttack) + HTfloat2int(this.rightAttack)) + 0.4 * HTfloat2int(this.centralAttack)) +
                0.22 * (0.3 * (HTfloat2int(this.leftDefense) + HTfloat2int(this.rightDefense)) + 0.4 * HTfloat2int(this.centralDefense));
    }

}
