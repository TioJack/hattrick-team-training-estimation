package net.ddns.tiojack.htte.service;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import net.ddns.tiojack.htte.model.PlayerTrainingRQ;
import net.ddns.tiojack.htte.model.Skill;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlayerTrainingService {

    public double getPlayerTraining(final PlayerTrainingRQ playerTrainingRQ) {
        //T = f(lvl) * K(coach) * K(assist) * K(int) * K(stam) * K(train) * K(age) * K(time)
        final double training = Math.min(1, this.getSkill(playerTrainingRQ.getSkill())
                * this.getCoefficientCoach(playerTrainingRQ.getCoach())
                * this.getCoefficientAssistants(playerTrainingRQ.getAssistants())
                * this.getCoefficientIntensity(playerTrainingRQ.getIntensity())
                * this.getCoefficientStamina(playerTrainingRQ.getStamina())
                * playerTrainingRQ.getTraining().getCoefficient()
                * this.getCoefficientAge(playerTrainingRQ.getAge()));

        return Math.max(0, training - this.getDropLevel(playerTrainingRQ.getSkill(), playerTrainingRQ.getAge()));
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

    private static final double A = 0.000006111;
    private static final double B = 0.000808;
    private static final double C = -0.026017;
    private static final double D = 0.192775;
    private static final double E = 0.39;

    private static final Map<Integer, Double> M = ImmutableMap.<Integer, Double>builder()
            .put(31, 0.00031)
            .put(32, 0.00118)
            .put(33, 0.00264)
            .put(34, 0.00468)
            .put(35, 0.00732)
            .put(36, 0.01066)
            .put(37, 0.01460)
            .build();

    private static final Map<Integer, Double> N = ImmutableMap.<Integer, Double>builder()
            .put(31, -0.00434)
            .put(32, -0.01625)
            .put(33, -0.03551)
            .put(34, -0.06086)
            .put(35, -0.09104)
            .put(36, -0.12554)
            .put(37, -0.16021)
            .build();

    private double getDropLevel(final double skill, final int age) {
        final double lvl = skill - 1;
        final double plus = lvl > 20 ? E : 0;
        final double dropLevel = 14 <= lvl ? A * Math.pow(lvl + plus, 3) + B * Math.pow(lvl + plus, 2) + C * (lvl + plus) + D : 0;
        final double factor = lvl > 20 ? lvl + 1 : lvl;
        final double dropLevelAge = age < 31 ? 0 : M.getOrDefault(age, 0.01460) * factor + N.getOrDefault(age, -0.16021);
        return dropLevel + dropLevelAge;
    }

    private static final Map<Integer, Double> AGE_LOSS = ImmutableMap.<Integer, Double>builder()
            .put(1, 0.0003)
            .put(2, 0.0014)
            .put(3, 0.0037)
            .put(4, 0.0074)
            .put(5, 0.0127)
            .put(6, 0.0197)
            .put(7, 0.0285)
            .put(8, 0.0393)
            .put(9, 0.0522)
            .put(10, 0.0673)
            .put(11, 0.0846)
            .build();

    private double getDropAge(final Skill skill, final int age) {
        final int ageLoss = age - skill.getAgeNoDrop();
        return ageLoss < 1 ? 0 : AGE_LOSS.getOrDefault(ageLoss, 0.0846);
    }

}
