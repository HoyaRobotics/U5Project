// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.generated.IntakeConstants;
import frc.robot.generated.ShooterConstants;

public class AutoShoot extends Command {
  Intake intake;
  Shooter shooter;
  double speed;

  private boolean finished = false;
  private boolean timeStampLock = true;
  private double shootTime = 0;

  /** Creates a new Shoot. */
  public AutoShoot(Shooter shooter, Intake intake, double speed) {
    this.shooter = shooter;
    this.speed = speed;
    this.intake = intake; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setShooterSpeeds(speed,0.0);
    intake.setIntakePosition(IntakeConstants.homePosition);
    timeStampLock = true;
    finished = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  if(shooter.isShooterAtSpeed(ShooterConstants.shootingRPM) && intake.isIntakeAtPosition(IntakeConstants.homePosition)){
    intake.setRollerSpeed(IntakeConstants.shootSpeed);
    if(timeStampLock){
      shootTime = Timer.getFPGATimestamp();
      timeStampLock = false;
    }

    if(!timeStampLock && Timer.getFPGATimestamp() - shootTime > 0.4){
      finished = true;
    }
  }
   else {System.out.println("NOT TO SPEED");}
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopShooter();
    intake.setIntakePosition(IntakeConstants.homePosition);
    intake.setRollerSpeed(IntakeConstants.stallSpeed);
    finished = false;
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
