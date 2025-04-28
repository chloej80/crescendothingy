package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants;

public class Climbing extends SubsystemBase {

    private TalonFX leader;
    private TalonFX follower;
    private TalonFXConfiguration leaderConfig;
    private TalonFXConfiguration followerConfig;
    private DigitalInput digiInputLow;
    private DigitalInput digiInputHigh;

    public Climbing () {
      leader = new TalonFX (Ports.ClimbingPorts.leaderPort);
      follower = new TalonFX (Ports.ClimbingPorts.followPort);
      leaderConfig = new TalonFXConfiguration();
      followerConfig = new TalonFXConfiguration();
      leader.setNeutralMode(NeutralModeValue.Brake);
      follower.setNeutralMode(NeutralModeValue.Brake);
      digiInputLow = new DigitalInput(Ports.ClimbingPorts.digiInputLowPort);
      digiInputHigh = new DigitalInput(Ports.ClimbingPorts.digiInputHighPort);
    }

    public  Command ClimbDownCmd () {
      if (beambreaklow()) {
        return this.run (() -> StopMotorsCmd());
      } else {
        return this.run (() -> RunMotorsDown());
      }
    }
    
    public  Command ClimbUpCmd () {
      if (beambreakhigh()) {
        return this.run (() -> StopMotorsCmd());

      } else {
        return this.run (() -> RunMotorsUP());
        
      }
    }

    public  Command StopMotorsCmd () {

     return this.run (() -> StopMotors());

    }

    public void RunMotorsUP () {
      leader.set(Constants.ClimbingConstants.RUN);
      follower.set(Constants.ClimbingConstants.RUN);
    }

    public void RunMotorsDown () {
      leader.set(-Constants.ClimbingConstants.RUN);
      follower.set(-Constants.ClimbingConstants.RUN);
    }
 
    public boolean beambreaklow () {
      return !digiInputLow.get();
    }

    public boolean beambreakhigh () {
      return !digiInputHigh.get();
    }

    public void StopMotors () {
      leader.set(0);
      follower.set(0);

    }
  

    



}
