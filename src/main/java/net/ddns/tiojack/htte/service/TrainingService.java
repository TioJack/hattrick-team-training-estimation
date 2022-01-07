package net.ddns.tiojack.htte.service;

import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.Training;
import net.ddns.tiojack.htte.model.TrainingRQ;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingService {

    public double getTraining(final TrainingRQ trainingRQ) {
        //T = f(lvl) * K(coach) * K(assist) * K( int) *K(stam) * K(train) * K(age) * K(time)
        double retVal = this.getSkill(trainingRQ.getSkill());
        retVal *= this.getCoefficientCoach(trainingRQ.getCoach());
        retVal *= this.getCoefficientAssistants(trainingRQ.getAssistants());
        retVal *= this.getCoefficientIntensity(trainingRQ.getIntensity());
        retVal *= this.getCoefficientStamina(trainingRQ.getStamina());
        retVal *= this.getCoefficientTraining(trainingRQ.getTraining());
        retVal *= this.getCoefficientAge(trainingRQ.getAge());
        // retVal *= this.getCoefficientTime(); // Always is 1
        return retVal;
    }

    private double getSkill(double skill) {
        skill -= 1;
        return skill < 9 ? 16.289 * Math.exp(-0.1396 * skill) : 54.676 / skill - 1.438;
    }

    private double getCoefficientCoach(final int coach) {
        return switch (coach) {
            case 8 -> 1.0375;
            case 7 -> 1.0000;
            case 6 -> 0.9200;
            case 5 -> 0.8324;
            case 4 -> 0.7343;
            default -> 0.0;
        };
    }

    private double getCoefficientAssistants(final int assistants) {
        return switch (assistants) {
            case 10 -> 1.350;
            case 9 -> 1.315;
            case 8 -> 1.280;
            case 7 -> 1.245;
            case 6 -> 1.210;
            case 5 -> 1.175;
            case 4 -> 1.140;
            case 3 -> 1.105;
            case 2 -> 1.070;
            case 1 -> 1.035;
            case 0 -> 1.0000;
            default -> 0.0;
        };
    }

    private double getCoefficientIntensity(final int intensity) {
        return intensity / 100.0;
    }

    private double getCoefficientStamina(final int stamina) {
        return 1.0 - (stamina / 100.0);
    }

    private double getCoefficientTraining(final Training training) {
        return switch (training) {
            case GOALKEEPING -> 0.0510;
            case DEFENDING -> 0.0288;
            case PLAY_MAKING -> 0.0336;
            case PLAY_MAKING_LESS -> 0.0168;
            case WINGER -> 0.0480;
            case WINGER_LESS -> 0.0240;
            case PASSING -> 0.0360;
            case SCORING -> 0.0324;
            case SET_PIECES -> 0.1470;
            case SET_PIECES_PLUS -> 0.18375;
        };
    }

    private double getCoefficientAge(final int age) {
        return 54.0 / (age + 37.0);
    }

}
