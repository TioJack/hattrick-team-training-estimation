package net.ddns.tiojack.htte.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Builder
@Value
@Jacksonized
public class PlayerRating implements Serializable {

    private static final long serialVersionUID = 3432080566408191085L;

    int playerId;
    Position position;
    Ratings rating;

}
