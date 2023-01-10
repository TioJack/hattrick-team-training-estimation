package net.ddns.tiojack.htte.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Map;

@Builder
@Value
@Jacksonized
public class TeamTrainingRQ implements Serializable {

    private static final long serialVersionUID = 5331342158872356541L;

    //<playerId,IPlayer>
    Map<Integer, Player> players;

    //<trainingStageId,TrainingStage>
    Map<Integer, TrainingStage> trainingStages;

    //<trainingStageId,<playerId,Training>>
    Map<Integer, Map<Integer, Training>> stagePlayerTraining;

    DensityRatingCalculation densityRatingCalculation;
    BestFormationCriteria bestFormationCriteria;
    MatchDetail matchDetail;

}
