// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.ClimberConstants;

public class Climber extends SubsystemBase {
  private TalonFX climberMotor = new TalonFX (ClimberConstants.climberMotorCanID);
  /** Creates a new Climber. */ 
  public Climber() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   configureMotor();
    SmartDashboard.putNumber("climber", climberMotor.getPosition().getValueAsDouble());
 
  }// end periodic
  public void moveClimber(double power){
    climberMotor.set(power);
  }

  public void stopClimber(){
    climberMotor.stopMotor();
  }

  public void configureMotor(){
    climberMotor.getConfigurator().apply(new TalonFXConfiguration());
    var talonfxConfigs = new TalonFXConfiguration();
    talonfxConfigs.Slot0 = ClimberConstants.climberSlot0Configs;
    talonfxConfigs.CurrentLimits = ClimberConstants.climberCurrentLimits;
    talonfxConfigs.Voltage = ClimberConstants.climberVoltageConfigs;
    talonfxConfigs.Feedback = ClimberConstants.climberFeedbackConfigs;
    talonfxConfigs.MotionMagic = ClimberConstants.climberMotionMagicConfigs;
    talonfxConfigs.SoftwareLimitSwitch = ClimberConstants.climberSoftwareLimitSwitchConfigs;
    talonfxConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    climberMotor.getConfigurator().apply(talonfxConfigs);
    climberMotor.setPosition(0.0);
  }

}//end class
