package net.ddns.tiojack.htte.model;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class RatingConfig {

    public static final Map<RatingConfigGroup, Map<RatingConfigSection, Map<RatingConfigProp, Double>>> config =
            ImmutableMap.<RatingConfigGroup, Map<RatingConfigSection, Map<RatingConfigProp, Double>>>builder()
                    .put(RatingConfigGroup.SIDEDEFENSE, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.delta, 0.75)
                                    .put(RatingConfigProp.power, 1.165)
                                    .put(RatingConfigProp.squareMod, 0.0)
                                    .put(RatingConfigProp.cubeMod, 0.0)
                                    .put(RatingConfigProp.extraMulti, 1.000)
                                    .put(RatingConfigProp.trainerOff, 0.9)
                                    .put(RatingConfigProp.trainerDef, 1.15)
                                    .put(RatingConfigProp.trainerNeutral, 1.0)
                                    .put(RatingConfigProp.tacticAIM, 0.853911)
                                    .put(RatingConfigProp.tacticCreative, 0.930999)
                                    .put(RatingConfigProp.defleadPercentDef, 0.0)
                                    .put(RatingConfigProp.pullback, 0.125)
                                    .build())
                            .put(RatingConfigSection.goalkeeping_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.GK_norm, 0.15555)
                                    .build())
                            .put(RatingConfigSection.defending_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.GK_norm, 0.06375)
                                    .build())
                            .put(RatingConfigSection.defending_thisside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.CD_norm, 0.1326)
                                    .put(RatingConfigProp.CD_off, 0.10200000000000001)
                                    .put(RatingConfigProp.CD_tw, 0.20655)
                                    .put(RatingConfigProp.WB_norm, 0.2346)
                                    .put(RatingConfigProp.WB_off, 0.1887)
                                    .put(RatingConfigProp.WB_tm, 0.19125)
                                    .put(RatingConfigProp.WB_def, 0.255)
                                    .put(RatingConfigProp.IM_norm, 0.04845)
                                    .put(RatingConfigProp.IM_off, 0.022949999999999998)
                                    .put(RatingConfigProp.IM_def, 0.06885000000000001)
                                    .put(RatingConfigProp.IM_tw, 0.0612)
                                    .put(RatingConfigProp.WI_norm, 0.08925)
                                    .put(RatingConfigProp.WI_off, 0.056100000000000004)
                                    .put(RatingConfigProp.WI_def, 0.15555)
                                    .put(RatingConfigProp.WI_tm, 0.07395)
                                    .build())
                            .build())
                    .put(RatingConfigGroup.CENTRALDEFENSE, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.delta, 0.75)
                                    .put(RatingConfigProp.power, 1.165)
                                    .put(RatingConfigProp.squareMod, 0.0)
                                    .put(RatingConfigProp.cubeMod, 0.0)
                                    .put(RatingConfigProp.extraMulti, 1.000)
                                    .put(RatingConfigProp.trainerOff, 0.9)
                                    .put(RatingConfigProp.trainerDef, 1.15)
                                    .put(RatingConfigProp.trainerNeutral, 1.0)
                                    .put(RatingConfigProp.tacticAOW, 0.858029)
                                    .put(RatingConfigProp.tacticCreative, 0.930999)
                                    .put(RatingConfigProp.defleadPercentDef, 0.0)
                                    .put(RatingConfigProp.pullback, 0.125)
                                    .build())
                            .put(RatingConfigSection.goalkeeping_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.GK_norm, 0.135333285)
                                    .build())
                            .put(RatingConfigSection.defending_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.GK_norm, 0.054444425000000005)
                                    .put(RatingConfigProp.CD_norm, 0.1555555)
                                    .put(RatingConfigProp.CD_off, 0.11355551500000001)
                                    .put(RatingConfigProp.CD_tw, 0.10422218500000001)
                                    .put(RatingConfigProp.WB_norm, 0.059111090000000005)
                                    .put(RatingConfigProp.WB_off, 0.054444425000000005)
                                    .put(RatingConfigProp.WB_tm, 0.10888885000000001)
                                    .put(RatingConfigProp.WB_def, 0.066888865)
                                    .put(RatingConfigProp.IM_norm, 0.062222200000000005)
                                    .put(RatingConfigProp.IM_off, 0.024888880000000002)
                                    .put(RatingConfigProp.IM_def, 0.09022219000000001)
                                    .put(RatingConfigProp.IM_tw, 0.051333315000000004)
                                    .put(RatingConfigProp.WI_norm, 0.031111100000000003)
                                    .put(RatingConfigProp.WI_off, 0.020222215)
                                    .put(RatingConfigProp.WI_def, 0.038888875)
                                    .put(RatingConfigProp.WI_tm, 0.038888875)
                                    .build())
                            .build())
                    .put(RatingConfigGroup.MIDFIELD, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.delta, 0.75)
                                    .put(RatingConfigProp.power, 1.165)
                                    .put(RatingConfigProp.squareMod, 0.0)
                                    .put(RatingConfigProp.cubeMod, 0.0)
                                    .put(RatingConfigProp.home, 1.199529)
                                    .put(RatingConfigProp.awayDerby, 1.113699)
                                    .put(RatingConfigProp.pic, 0.839949)
                                    .put(RatingConfigProp.mots, 1.109650)
                                    .put(RatingConfigProp.teamSpiritPreMulti, 0.147832)
                                    .put(RatingConfigProp.teamSpiritPower, 0.417779)
                                    .put(RatingConfigProp.extraMulti, 1.000)
                                    .put(RatingConfigProp.tacticCounter, 0.930000)
                                    .put(RatingConfigProp.tacticLongShots, 0.950323)
                                    .build())
                            .put(RatingConfigSection.playmaking_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.CD_norm, 0.02775)
                                    .put(RatingConfigProp.CD_off, 0.0444)
                                    .put(RatingConfigProp.CD_tw, 0.016649999999999998)
                                    .put(RatingConfigProp.WB_norm, 0.016649999999999998)
                                    .put(RatingConfigProp.WB_off, 0.0222)
                                    .put(RatingConfigProp.WB_def, 0.0111)
                                    .put(RatingConfigProp.WB_tm, 0.0222)
                                    .put(RatingConfigProp.IM_norm, 0.111)
                                    .put(RatingConfigProp.IM_off, 0.10545)
                                    .put(RatingConfigProp.IM_def, 0.10545)
                                    .put(RatingConfigProp.IM_tw, 0.0999)
                                    .put(RatingConfigProp.WI_norm, 0.04995)
                                    .put(RatingConfigProp.WI_off, 0.033299999999999996)
                                    .put(RatingConfigProp.WI_def, 0.033299999999999996)
                                    .put(RatingConfigProp.WI_tm, 0.06105000000000001)
                                    .put(RatingConfigProp.FW_norm, 0.02775)
                                    .put(RatingConfigProp.FW_def, 0.038849999999999996)
                                    .put(RatingConfigProp.FW_tw, 0.016649999999999998)
                                    .build())
                            .build())
                    .put(RatingConfigGroup.SIDEATTACK, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.delta, 0.75)
                                    .put(RatingConfigProp.power, 1.165)
                                    .put(RatingConfigProp.trainerOff, 1.1)
                                    .put(RatingConfigProp.trainerDef, 0.9)
                                    .put(RatingConfigProp.trainerNeutral, 1.0)
                                    .put(RatingConfigProp.confidence, 0.0525)
                                    .put(RatingConfigProp.tacticLongShots, 0.972980)
                                    .put(RatingConfigProp.pullback, -0.25)
                                    .build())
                            .put(RatingConfigSection.passing_middle, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.IM_norm, 0.02483)
                                    .put(RatingConfigProp.IM_off, 0.03438)
                                    .put(RatingConfigProp.IM_def, 0.013370000000000002)
                                    .build())
                            .put(RatingConfigSection.passing_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_norm, 0.026740000000000003)
                                    .put(RatingConfigProp.FW_def, 0.05921)
                                    .put(RatingConfigProp.FW_def_tech, 0.07830999999999999)
                                    .build())
                            .put(RatingConfigSection.passing_thisside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.IM_norm, 0.04966)
                                    .put(RatingConfigProp.IM_off, 0.06876)
                                    .put(RatingConfigProp.IM_def, 0.026740000000000003)
                                    .put(RatingConfigProp.IM_tw, 0.05921)
                                    .put(RatingConfigProp.WI_norm, 0.04966)
                                    .put(RatingConfigProp.WI_off, 0.055389999999999995)
                                    .put(RatingConfigProp.WI_def, 0.04011)
                                    .put(RatingConfigProp.WI_tm, 0.02865)
                                    .put(RatingConfigProp.FW_tw, 0.04011)
                                    .build())
                            .put(RatingConfigSection.passing_otherside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_tw, 0.01146)
                                    .build())
                            .put(RatingConfigSection.winger_thisside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.CD_tw, 0.04966)
                                    .put(RatingConfigProp.WB_norm, 0.11269)
                                    .put(RatingConfigProp.WB_off, 0.13179)
                                    .put(RatingConfigProp.WB_tm, 0.06684999999999999)
                                    .put(RatingConfigProp.WB_def, 0.08595)
                                    .put(RatingConfigProp.IM_tw, 0.11269)
                                    .put(RatingConfigProp.WI_norm, 0.16426)
                                    .put(RatingConfigProp.WI_off, 0.191)
                                    .put(RatingConfigProp.WI_def, 0.13179)
                                    .put(RatingConfigProp.WI_tm, 0.14134)
                                    .put(RatingConfigProp.FW_tw, 0.12224)
                                    .build())
                            .put(RatingConfigSection.winger_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_norm, 0.04584)
                                    .put(RatingConfigProp.FW_def, 0.02483)
                                    .put(RatingConfigProp.FW_def_tech, 0.02483)
                                    .build())
                            .put(RatingConfigSection.winger_otherside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_tw, 0.04011)
                                    .build())
                            .put(RatingConfigSection.scoring_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_norm, 0.051570000000000005)
                                    .put(RatingConfigProp.FW_def, 0.02483)
                                    .put(RatingConfigProp.FW_def_tech, 0.02483)
                                    .build())
                            .put(RatingConfigSection.scoring_otherside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_tw, 0.03629)
                                    .build())
                            .put(RatingConfigSection.scoring_thisside, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.FW_tw, 0.09741)
                                    .build())
                            .build())
                    .put(RatingConfigGroup.CENTRALATTACK, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.delta, 0.75)
                                    .put(RatingConfigProp.power, 1.165)
                                    .put(RatingConfigProp.trainerOff, 1.1)
                                    .put(RatingConfigProp.trainerDef, 0.9)
                                    .put(RatingConfigProp.confidence, 0.0525)
                                    .put(RatingConfigProp.trainerNeutral, 1.0)
                                    .put(RatingConfigProp.tacticLongShots, 0.970577)
                                    .put(RatingConfigProp.pullback, -0.25)
                                    .build())
                            .put(RatingConfigSection.passing_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.IM_norm, 0.0533775)
                                    .put(RatingConfigProp.IM_off, 0.0792575)
                                    .put(RatingConfigProp.IM_def, 0.029115)
                                    .put(RatingConfigProp.IM_tw, 0.0372025)
                                    .put(RatingConfigProp.WI_norm, 0.0177925)
                                    .put(RatingConfigProp.WI_off, 0.0210275)
                                    .put(RatingConfigProp.WI_def, 0.008087500000000001)
                                    .put(RatingConfigProp.WI_tm, 0.02588)
                                    .put(RatingConfigProp.FW_norm, 0.0533775)
                                    .put(RatingConfigProp.FW_def, 0.08572750000000001)
                                    .put(RatingConfigProp.FW_def_tech, 0.08572750000000001)
                                    .put(RatingConfigProp.FW_tw, 0.0372025)
                                    .build())
                            .put(RatingConfigSection.scoring_allsides, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.IM_norm, 0.035585)
                                    .put(RatingConfigProp.IM_off, 0.0501425)
                                    .put(RatingConfigProp.IM_def, 0.0210275)
                                    .put(RatingConfigProp.FW_norm, 0.16175)
                                    .put(RatingConfigProp.FW_def, 0.09058000000000001)
                                    .put(RatingConfigProp.FW_def_tech, 0.09058000000000001)
                                    .put(RatingConfigProp.FW_tw, 0.106755)
                                    .build())
                            .build())
                    .put(RatingConfigGroup.PLAYERSTRENGTH, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.formMin, 1.0)
                                    .put(RatingConfigProp.formMax, 8.0)
                                    .put(RatingConfigProp.formDelta, 0.0)
                                    .put(RatingConfigProp.formMultiplier, 0.125)
                                    .put(RatingConfigProp.formPower, 0.6666666666666666)
                                    .put(RatingConfigProp.resultMultiForm, 1.0)
                                    .put(RatingConfigProp.multiXpLog10, 1.33333333333333333333333333333333333333333333333333333333333)
                                    .put(RatingConfigProp.resultAddXp, 1.0)
                                    .put(RatingConfigProp.skillDelta, 0.0)
                                    .put(RatingConfigProp.skillMin, 0.0)
                                    .put(RatingConfigProp.weatherBonus, 0.05)
                                    .put(RatingConfigProp.twoCdMulti, 0.95)
                                    .put(RatingConfigProp.threeCdMulti, 0.90)
                                    .put(RatingConfigProp.twoMfMulti, 0.9)
                                    .put(RatingConfigProp.threeMfMulti, 0.8)
                                    .put(RatingConfigProp.twoFwMulti, 0.94)
                                    .put(RatingConfigProp.threeFwMulti, 0.865)
                                    .build())
                            .build())
                    .put(RatingConfigGroup.TACTICS, ImmutableMap.<RatingConfigSection, Map<RatingConfigProp, Double>>builder()
                            .put(RatingConfigSection.general, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.delta, 0.000000)
                                    .put(RatingConfigProp.extraMulti, 1.000000)
                                    .put(RatingConfigProp.squareMod, 0.000000)
                                    .put(RatingConfigProp.cubeMod, 0.000000)
                                    .build())
                            .put(RatingConfigSection.aim_aow, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.postMulti, 0.194912)
                                    .put(RatingConfigProp.squareMod, 0.009067)
                                    .put(RatingConfigProp.cubeMod, -0.000351)
                                    .build())
                            .put(RatingConfigSection.counter, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.multiPs, 0.23)
                                    .put(RatingConfigProp.multiDe, 0.115)
                                    .build())
                            .put(RatingConfigSection.pressing, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.multiDe, 0.085)
                                    .put(RatingConfigProp.postDelta, 0.075)
                                    .build())
                            .put(RatingConfigSection.longshots, ImmutableMap.<RatingConfigProp, Double>builder()
                                    .put(RatingConfigProp.multiSc, 0.166666666666667)
                                    .put(RatingConfigProp.multiSp, 0.0555555555555556)
                                    .put(RatingConfigProp.postDelta, -7.6)
                                    .build())
                            .build())
                    .build();

}
