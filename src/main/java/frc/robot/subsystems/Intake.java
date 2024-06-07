// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.IntakeConstants;

public class Intake extends SubsystemBase {

  private final TalonFX rotationMotor = new TalonFX(22);
  private final static CANSparkFlex rollerMotor = new CANSparkFlex(23, MotorType.kBrushless);
  /** Creates a new Intake. */
  private final MotionMagicVoltage magicRequest = new MotionMagicVoltage(0);

  public Intake() {
    configureRotationMotor();
    configureRollerMotor();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake Roller Position",rotationMotor.getPosition().getValueAsDouble());
    // This method will be called once per scheduler run
  }

  public void configureRotationMotor(){
    rotationMotor.getConfigurator().apply(new TalonFXConfiguration());
    TalonFXConfiguration talonfxConfigs = new TalonFXConfiguration();
    talonfxConfigs.Slot0 = IntakeConstants.rotationSlot0Configs;
    talonfxConfigs.CurrentLimits = IntakeConstants.rotationCurrentLimits;
    talonfxConfigs.Voltage = IntakeConstants.rotationVoltageConfigs;
    talonfxConfigs.Feedback = IntakeConstants.rotationFeedbackConfigs;
    talonfxConfigs.MotionMagic = IntakeConstants.rotationMotionMagicConfigs;
    talonfxConfigs.SoftwareLimitSwitch = IntakeConstants.rotationSoftwareLimitSwitchConfigs;
    talonfxConfigs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    rotationMotor.getConfigurator().apply(talonfxConfigs);
    rotationMotor.setPosition(IntakeConstants.homePosition);
    setIntakePosition(IntakeConstants.homePosition);
  }
  public void configureRollerMotor(){
    rollerMotor.restoreFactoryDefaults();
    rollerMotor.setIdleMode(IdleMode.kCoast);
    rollerMotor.setSmartCurrentLimit(IntakeConstants.rollerMotorCurrentLimit);
    rollerMotor.setInverted(true);
    rollerMotor.set(IntakeConstants.stallSpeed);
  }
  public void setIntakePosition(double position){
    rotationMotor.setControl(magicRequest.withPosition(position).withSlot(0));
  }
  public void stopPosition(){
    rotationMotor.stopMotor();
  }
  public static void setRollerSpeed(double speed){
    rollerMotor.set(speed);
  }
  public void stopRoller(){
    rollerMotor.stopMotor();
  }
  public boolean isIntakeAtPosition(double position){
    double error = Math.abs(position-rotationMotor.getPosition().getValueAsDouble());
    if(error < IntakeConstants.positionError){
      return true;
    }else{
      return false;
    }
  }
}
