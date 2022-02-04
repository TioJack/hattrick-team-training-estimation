package net.ddns.tiojack.htte.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@Builder
@Value
@Jacksonized
public class FormationRating implements Serializable {

    private static final long serialVersionUID = 5887481640724161812L;

    String formation;
    List<PlayerRating> players;
    Ratings rating;

}
