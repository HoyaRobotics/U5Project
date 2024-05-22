
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.generated;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;

/** Add your docs here. */
public class ClimberConstants {
    public static final int climberMotorCanID = 27;
    public static final double climberGearRatio = 45;
    public static final Slot0Configs climberSlot0Configs = new Slot0Configs()
        .withGravityType(GravityTypeValue.Arm_Cosine)
        .withKG(climberGearRatio)
        .withKS(0.0)
        .withKV(0.0)
        .withKA(0.0)
        .withKP(0.0)
        .withKI(0.0)
        .withKD(0.0);
    public static final CurrentLimitsConfigs climberCurrentLimits = new CurrentLimitsConfigs()
        .withStatorCurrentLimit(50.0)
        .withStatorCurrentLimitEnable(true);
    public static final VoltageConfigs climberVoltageConfigs = new VoltageConfigs()
        .withPeakForwardVoltage(10.0)
        .withPeakReverseVoltage(10.0);
    public static final FeedbackConfigs climberFeedbackConfigs = new FeedbackConfigs()
        .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor)
        .withSensorToMechanismRatio(climberGearRatio);
    public static final MotionMagicConfigs climberMotionMagicConfigs = new MotionMagicConfigs()
        .withMotionMagicAcceleration(0.0)
        .withMotionMagicCruiseVelocity(0.0)
        .withMotionMagicExpo_kA(0.0)
        .withMotionMagicExpo_kV(0)
        .withMotionMagicJerk(0.0);
    public static final SoftwareLimitSwitchConfigs climberSoftwareLimitSwitchConfigs = new SoftwareLimitSwitchConfigs()
        .withForwardSoftLimitEnable(false)
        .withForwardSoftLimitThreshold(0)
        .withReverseSoftLimitEnable(false)
        .withReverseSoftLimitThreshold(0.0);

    public static final double positionError = 0.05;
}
