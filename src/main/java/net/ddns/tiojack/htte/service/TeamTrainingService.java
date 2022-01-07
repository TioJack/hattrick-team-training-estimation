package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.TeamTrainingRQ;
import net.ddns.tiojack.htte.model.TeamTrainingRS;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamTrainingService {

    public TeamTrainingRS getTeamTraining(final TeamTrainingRQ teamTrainingRQ) {


        //IntStream.range(1, teamTrainingRQ.getTrainingSteps().stream().mapToInt(TrainingStep::getDuration).sum()).collect(Col)

        return null;
    }

}
