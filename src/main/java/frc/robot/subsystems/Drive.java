// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Constants.*;
import static edu.wpi.first.math.MathUtil.applyDeadband;

public class Drive extends SubsystemBase {
  private CANSparkMax leftMaster, leftSlave;
  private CANSparkMax rightMaster, rightSlave;
  private DifferentialDrive drivetrain;

  private static Drive instance;

  private Drive() {
    // Initialize motors
    this.leftMaster = new CANSparkMax(DRIVE_LEFT_MASTER_CHANNEL, MotorType.kBrushless);
    this.leftSlave = new CANSparkMax(DRIVE_LEFT_SLAVE_CHANNEL, MotorType.kBrushless);
    this.rightMaster = new CANSparkMax(DRIVE_RIGHT_MASTER_CHANNEL, MotorType.kBrushless);
    this.rightSlave = new CANSparkMax(DRIVE_RIGHT_SLAVE_CHANNEL, MotorType.kBrushless);

    // Initialize drivetrain
    MotorControllerGroup leftGroup = new MotorControllerGroup(leftMaster, leftSlave);
    MotorControllerGroup righGroup = new MotorControllerGroup(rightMaster, rightSlave);
    this.drivetrain = new DifferentialDrive(leftGroup, righGroup);

    // Miscellaneous initialization
    configureDrivetrain();
  }

  public static Drive getInstance() {
    if (instance == null)
      instance = new Drive();
    return instance;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Master Speed", leftMaster.get());
    SmartDashboard.putNumber("Left Slave Speed", leftSlave.get());
    SmartDashboard.putNumber("Right Master Speed", rightMaster.get());
    SmartDashboard.putNumber("Right Slave Speed", rightSlave.get());

    SmartDashboard.putNumber("Left Master Voltage", leftMaster.getBusVoltage());
    SmartDashboard.putNumber("Left Slave Voltage", leftSlave.getBusVoltage());
    SmartDashboard.putNumber("Right Master Voltage", rightMaster.getBusVoltage());
    SmartDashboard.putNumber("Right Slave Voltage", rightSlave.getBusVoltage());

    SmartDashboard.putNumber("Left Master Amperage", leftMaster.getAppliedOutput());
    SmartDashboard.putNumber("Left Slave Amperage", leftSlave.getAppliedOutput());
    SmartDashboard.putNumber("Right Master Amperage", rightMaster.getAppliedOutput());
    SmartDashboard.putNumber("Right Slave Amperage", rightSlave.getAppliedOutput());
  }

  public void arcadeDrive(CommandXboxController controller) {
    double leftTrigger = applyDeadband(controller.getLeftTriggerAxis(), DRIVER_TRIGGER_DEADBAND);
    double rightTrigger = applyDeadband(controller.getRightTriggerAxis(), DRIVER_TRIGGER_DEADBAND);
    double rightStick = applyDeadband(controller.getRightX(), DRIVER_STICK_DEADBAND);

    // Calculate speed and rotation
    double speed = rightTrigger - leftTrigger;
    double rotation = rightStick;

    // Apply inverts
    speed *= DRIVE_SPEED_INVERT;
    rotation *= DRIVE_ROTATION_INVERT;

    // Apply inhibitors
    speed *= DRIVE_SPEED_INHIBITOR;
    rotation *= DRIVE_ROTATION_INHIBITOR;

    // Update drivetrain
    drivetrain.arcadeDrive(speed, rotation);
  }



  private void configureDrivetrain() {
    leftMaster.setInverted(DRIVE_LEFT_MASTER_INVERT);
    leftSlave.setInverted(DRIVE_LEFT_SLAVE_INVERT);
    rightMaster.setInverted(DRIVE_RIGHT_MASTER_INVERT);
    rightSlave.setInverted(DRIVE_RIGHT_SLAVE_INVERT);
  }

}
