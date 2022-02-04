package net.ddns.tiojack.htte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DensityRatingCalculation {
    WITHOUT,
    STAGE,
    SEASON,
    COMPLETE;
}
