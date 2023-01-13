// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  public static final int DRIVE_LEFT_MASTER_CHANNEL = 1;
  public static final int DRIVE_LEFT_SLAVE_CHANNEL = 4;
  public static final int DRIVE_RIGHT_MASTER_CHANNEL = 2;
  public static final int DRIVE_RIGHT_SLAVE_CHANNEL = 6;

  public static final boolean DRIVE_LEFT_MASTER_INVERT = false;
  public static final boolean DRIVE_LEFT_SLAVE_INVERT = false;
  public static final boolean DRIVE_RIGHT_MASTER_INVERT = true;
  public static final boolean DRIVE_RIGHT_SLAVE_INVERT = true;
  
  public static final double DRIVE_SPEED_INVERT = 1D;
  public static final double DRIVE_ROTATION_INVERT = -1D;
  public static final double DRIVE_SPEED_INHIBITOR = 0.33D;
  public static final double DRIVE_ROTATION_INHIBITOR = 0.33D;

  public static final double DRIVER_TRIGGER_DEADBAND = 0.1D;
  public static final double DRIVER_STICK_DEADBAND = 0.1D;
}
