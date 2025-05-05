package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends SubsystemBase {
    private TalonFX runLeader;
    private TalonFX runFollower;
    private TalonFX angleLeader;
    private TalonFX angleFollower;
    private DigitalInput digiInput;
    private ElevatorFeedforward feedforward;
    private PIDController pidController;
    private Encoder encoder; 


    public Shooter () {
      runLeader = new TalonFX (Ports.ClimbingPorts.followPort);
      runFollower = new TalonFX (Ports.ClimbingPorts.followPort);
      angleLeader = new TalonFX (Ports.ClimbingPorts.leaderPort);
      angleFollower = new TalonFX (Ports.ClimbingPorts.followPort);
      angleLeader.setNeutralMode(NeutralModeValue.Brake);
      angleFollower.setNeutralMode(NeutralModeValue.Brake);
      digiInput = new DigitalInput(Ports.ClimbingPorts.digiInputLowPort);
      feedforward = new ElevatorFeedforward(Constants.ShooterConstants.kS, Constants.ShooterC+onstants.kG, Constants.ShooterConstants.kV, Constants.ShooterConstants.kA);
      pidController = new PIDController(Constants.ShooterConstants.kP, Constants.ShooterConstants.kI, Constants.ShooterConstants.kD);
      encoder = new Encoder (Ports.ClimbingPorts, Ports.ClimbingPorts); // Replace with actual encoder ports
   
    }

    private double currentAngle = ((encoder.get())/(double) Constans.ShooterConstants.TICKS_PER_REVOLUTION)*360; // Get the current angle from the encoder
    private double currentVelocity = encoder.getRate(); // Replace with actual velocity if available
    private double feedforwardValue = feedforward.calculate(currentAngle, currentVelocity);

    





    public int getDegrees(){
  
        int ticks = encoder.get(); 
        return (int) (ticks / (double) Constants.ShooterConstants.TICKS_PER_REVOLUTION) * 360;

    }

    public Command angleUpCmd () {
        return this.run (() -> angleUp());
    }

    public Command angleDownCmd () {
        return this.run (() -> angleDown());
    }


    public void angleUp () {
        if (ticks < Constants.ShooterConstants.SHOOTER_ANGLE_MAX) {
            angleLeader.setVoltage(pid.calculate(encoder.get(), Constants.ShooterConstants.SHOOTER_ANGLE_MAX) + feedforwardValue);
            angleFollower.setVoltage(pid.calculate(encoder.get(), Constants.ShooterConstants.SHOOTER_ANGLE_MAX) + feedforwardValue);
        } else {
            StopMotors();
        }
    }

    public void angleDown () {

        double adjustedFeedforwardValue = -feedforwardValue;
        if (ticks > Constants.ShooterConstants.SHOOTER_ANGLE_MIN) {
            angleLeader.setVoltage(pid.calculate(encoder.get(), Constants.ShooterConstants.SHOOTER_ANGLE_MIN) + adjustedFeedforwardValue);
            angleFollower.setVoltage(pid.calculate(encoder.get(), Constants.ShooterConstants.SHOOTER_ANGLE_MIN) + adjustedFeedforwardValue);
        } else {
            StopMotors();
        }
    }

    public Command ShootCmd () {
        return this.run (() -> Shoot());
    }

    public void Shoot () {
        if (beambreakfront()) {
            StopMotors();
        } else {
            RunShooter();
        }
    }

    public Command RunAngleCmd () {
        return this.run (() -> RunAngle());
    }

    public Command RunShooterCmd () {
        return this.run (() -> RunShooter());
    }

    public Command RunShooterBKWDcmd () {
        return this.run (() -> RunShooterBKWD());
    }

    public Command RunAngleBKWDcmd () {
        return this.run (() -> RunAngleBKWD());
    }



    public void RunShooter () {
        runLeader.set(Constants.ShooterConstants.SHOOTER_RUN);
        runFollower.set(Constants.ShooterConstants.SHOOTER_RUN);
    }

    public void RunShooterBKWD () {
        runLeader.set(-Constants.ShooterConstants.SHOOTER_RUN);
        runFollower.set(-Constants.ShooterConstants.SHOOTER_RUN);
    }

    public void RunAngleBKWD () {
        angleLeader.set(-Constants.ShooterConstants.SHOOTER_ANGLE_SPEED);
        angleFollower.set(-Constants.ShooterConstants.SHOOTER_ANGLE_SPEED);
    }

    public void RunAngle () {
        angleLeader.set(Constants.ShooterConstants.SHOOTER_ANGLE_SPEED);
        angleFollower.set(Constants.ShooterConstants.SHOOTER_ANGLE_SPEED);
    }


    public  Command StopMotorsCmd () {

        return this.run (() -> StopMotors());
   
       }

    public void StopAngleMotors () {
        angleLeader.set(0);
        angleFollower.set(0);
    }

    public void StopRunMotors () {
        runLeader.set(0);
        runFollower.set(0);
    }


    public void StopMotors () {
        runLeader.set(0);
        runFollower.set(0);
        angleLeader.set(0);
        angleFollower.set(0);
    }

    public boolean beambreakfront () {
        return !digiInputFront.get();
      }



}
