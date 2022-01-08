package net.ddns.tiojack.htte.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.PlayerTrainingRQ;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlayerTrainingService {

    public double getPlayerTraining(final PlayerTrainingRQ playerTrainingRQ) {
        //T = f(lvl) * K(coach) * K(assist) * K(int) * K(stam) * K(train) * K(age) * K(time)
        return Math.min(1, this.getSkill(playerTrainingRQ.getSkill())
                * this.getCoefficientCoach(playerTrainingRQ.getCoach())
                * this.getCoefficientAssistants(playerTrainingRQ.getAssistants())
                * this.getCoefficientIntensity(playerTrainingRQ.getIntensity())
                * this.getCoefficientStamina(playerTrainingRQ.getStamina())
                * playerTrainingRQ.getTraining().getCoefficient()
                * this.getCoefficientAge(playerTrainingRQ.getAge()));
    }

    private double getSkill(final double skill) {
        final double lvl = skill - 1;
        return lvl < 9 ? 16.289 * Math.exp(-0.1396 * lvl) : 54.676 / lvl - 1.438;
    }

    private static final Map<Integer, Double> coefficientCoach = ImmutableMap.<Integer, Double>builder()
            .put(4, 0.7343)
            .put(5, 0.8324)
            .put(6, 0.9200)
            .put(7, 1.0000)
            .put(8, 1.0375)
            .build();

    private double getCoefficientCoach(final int coach) {
        return coefficientCoach.getOrDefault(coach, 0.0);
    }

    private static final Map<Integer, Double> coefficientAssistants = ImmutableMap.<Integer, Double>builder()
            .put(0, 1.000)
            .put(1, 1.035)
            .put(2, 1.070)
            .put(3, 1.105)
            .put(4, 1.140)
            .put(5, 1.175)
            .put(6, 1.210)
            .put(7, 1.245)
            .put(8, 1.280)
            .put(9, 1.315)
            .put(10, 1.350)
            .build();

    private double getCoefficientAssistants(final int assistants) {
        return coefficientAssistants.getOrDefault(assistants, 0.0);
    }

    private double getCoefficientIntensity(final int intensity) {
        return intensity / 100.0;
    }

    private double getCoefficientStamina(final int stamina) {
        return 1.0 - (stamina / 100.0);
    }

    private double getCoefficientAge(final int age) {
        return 54.0 / (age + 37.0);
    }

}
