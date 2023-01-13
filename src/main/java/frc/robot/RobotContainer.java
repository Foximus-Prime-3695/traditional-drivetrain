// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ManualCommandDrive;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  // Controllers
  private static CommandXboxController driver;
  
  // Subsystems
  private final Drive drive = Drive.getInstance();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureBindings();
  }

  /**
   * Configures default bindings for the driver and operator controllers.
   */
  private void configureBindings() {
    drive.setDefaultCommand(new ManualCommandDrive(drive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  public static CommandXboxController getDriver() {
    if (driver == null)
      driver = new CommandXboxController(0);
    return driver;
  }
}
