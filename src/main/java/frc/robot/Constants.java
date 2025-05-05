// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ClimbingConstants {
    public static final int RUN = 0;
  }

  public static class HopperIntakeConstants {
    public static final int HOPPERRUN = 0;
    public static final int INTAKERUN = 0;
  }

  public static class ShooterConstants {
    public static final int SHOOTER_RUN = 0;
    
    public static final int SHOOTER_ANGLE_MAX = 0;
    public static final int SHOOTER_ANGLE_MIN = 0;

    public static final double SHOOTER_ANGLE_SPEED = 0.0;

    public static final double POS_CFACTOR = 360.0;

    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double kS = 0;
    public static final double kG = 0;
    public static final double kV = 0;
    public static final double kA = 0;

    public static final double TICKS_PER_REVOLUTION = 2048.0; // Example value, adjust as needed
  }
}
