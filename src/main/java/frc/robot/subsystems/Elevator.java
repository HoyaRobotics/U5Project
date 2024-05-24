// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.generated.ElevatorConstants;
import frc.robot.commands.MoveElevator;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  public Elevator() {
    configureMotorControllers();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void configureMotorControllers() {
    leftElevatorMotor.getConfigurator().apply(new TalonFXConfiguration());
    //rightElevator.getConfigurator().apply(new TalonFXConfiguration());
    var talonfxConfigs = new TalonFXConfiguration();
    talonfxConfigs.Slot0 = ElevatorConstants.elevatorSlot0Configs;
    talonfxConfigs.CurrentLimits = ElevatorConstants.elevatorCurrentLimits;
    talonfxConfigs.Voltage = ElevatorConstants.elevatorVoltageConfigs;
    talonfxConfigs.Feedback = ElevatorConstants.elevatorFeedbackConfigs;
    talonfxConfigs.MotionMagic = ElevatorConstants.elevatorMotionMagicConfigs;
    talonfxConfigs.SoftwareLimitSwitch = ElevatorConstants.elevatorSoftwareLimitSwitchConfigs;
    talonfxConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    leftElevatorMotor.getConfigurator().apply(talonfxConfigs);
    //rightElevator.getConfigurator().apply(talonfxConfigs);
    leftElevatorMotor.setPosition(0.0);
    //rightElevator.setPosition(0.0);
    leftElevatorMotor.setInverted(true);
  }
}
