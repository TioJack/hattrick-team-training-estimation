package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BestFormationCriteria {
    HATSTATS(
            Comparator.comparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    RIGHT_DEFENSE(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getRightDefense())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getRightDefense())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    CENTRAL_DEFENSE(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getCentralDefense())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getCentralDefense())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    LEFT_DEFENSE(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getLeftDefense())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getLeftDefense())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    DEFENSE(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getDefense())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getDefense())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    MIDFIELD(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getMidfield())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getMidfield())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    MIDFIELD3(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getMidfield3())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getMidfield3())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    RIGHT_ATTACK(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getRightAttack())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getRightAttack())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    CENTRAL_ATTACK(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getCentralAttack())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getCentralAttack())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    LEFT_ATTACK(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getLeftAttack())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getLeftAttack())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    ATTACK(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getAttack())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getAttack())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    ),
    PEASO_STATS(
            Comparator.comparingDouble((PlayerRating obj) -> obj.getRating().getPeasoStats())
                    .thenComparingInt((PlayerRating obj) -> obj.getRating().getHatStats())
                    .reversed(),
            Comparator.comparingDouble((FormationRating obj) -> obj.getRating().getPeasoStats())
                    .thenComparingInt((FormationRating obj) -> obj.getRating().getHatStats())
                    .reversed()
    );

    private final Comparator<PlayerRating> playerRatingComparator;
    private final Comparator<FormationRating> formationRatingComparator;
}
