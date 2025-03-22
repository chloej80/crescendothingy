package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
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

    public static Command ClimbDownCmd () {

    }
    
    public static Command ClimbUpCmd () {

    }

    public static void ClimbDown () {

    }

    public static void ClimbUp () {

    }

    



}
