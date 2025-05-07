package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import frc.robot.Ports;
import frc.robot.Constants;
import edu.wpi.first.math.controller.DifferentialDriveFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive extends SubsystemBase {
    private TalonFX right;
    private TalonFX left;
    private DifferentialDriveFeedforward feedforward;
    private PIDController pidController;
    private TalonFXConfiguration config;
    private DifferentialDrive differentialDrive;

    public Drive () {
        right = new TalonFX (Ports.DrivePorts.runLeaderPort);
        left = new TalonFX (Ports.DrivePorts.runFollowerPort);
        feedforward = new DifferentialDriveFeedforward(Constants.DriveConstants.kS, Constants.DriveConstants.kV, Constants.DriveConstants.kA);
        pidController = new PIDController(Constants.DriveConstants.kP, Constants.DriveConstants.kI, Constants.DriveConstants.kD);
        config = new TalonFXConfiguration();
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        left.getConfigurator().apply(config);
        differentialDrive = new DifferentialDrive(left, right);
    }

   
        double leftFeedforward = feedforward.calculate(Constants.DriveConstants.DESIRED_SPEED);
        double rightFeedforward = feedforward.calculate(Constants.DriveConstants.DESIRED_SPEED);
        
        double leftOutput = leftPID.calculate(left.get(), Constants.DriveConstants.DESIRED_SPEED) + leftFeedforward;
        double rightOutput = rightPID.calculate(right.get(), Constants.DriveConstants.DESIRED_SPEED) + rightFeedforward;
    
    
    
  

    public Command leftMotorCmd () {
        return this.run (() -> leftMotor());
    }

    public Command rightMotorCmd () {
        return this.run (() -> rightMotor());
    }

    public void leftMotor () {
        left.setVoltage(leftOutput);
    }

    public void rightMotor () {
        right.setVoltage(rightOutput);
    }


    public Command StopMotors () {
        return this.run (() -> DriveStopMotors());
  
      }

    public void DriveStopMotors () {
        right.set(0);
        left.set(0);
  
    }

}
