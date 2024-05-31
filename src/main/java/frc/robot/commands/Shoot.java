// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.generated.IntakeConstants;
//import frc.robot.generated.ShooterConstants;
import frc.robot.generated.ShooterConstants;

public class Shoot extends Command {
  Intake intake;
  Shooter shooter;
  double speed;

  /** Creates a new Shoot. */
  public Shoot(Shooter shooter, Intake intake, double speed) {
    this.shooter = shooter;
    this.speed = speed;
    this.intake = intake; 
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setShooterSpeeds(speed,0.05);
    intake.setIntakePosition(IntakeConstants.homePosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  if(shooter.isShooterAtSpeed(ShooterConstants.shootingRPM)&& intake.isIntakeAtPosition(IntakeConstants.homePosition)){
    Intake.setRollerSpeed(IntakeConstants.shootSpeed);
  }
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopShooter();
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
