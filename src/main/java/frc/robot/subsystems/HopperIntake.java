package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants;


public class HopperIntake extends SubsystemBase {
    private TalonFX intakeLeader;
    private TalonFX intakeFollower;
    private TalonFX hopperLeader;
    private TalonFX hopperFollower;
    private DigitalInput digiInputBack;
    private DigitalInput digiInputFront;

    public HopperIntake () {
      intakeLeader = new TalonFX (Ports.ClimbingPorts.leaderPort);
      intakeFollower = new TalonFX (Ports.ClimbingPorts.followPort);
      hopperLeader = new TalonFX (Ports.ClimbingPorts.leaderPort);
      hopperFollower = new TalonFX (Ports.ClimbingPorts.followPort);
      digiInputBack = new DigitalInput(Ports.ClimbingPorts.digiInputLowPort);
      digiInputFront = new DigitalInput(Ports.ClimbingPorts.digiInputHighPort);
    }

    public Command IntakeInCmd () {
            return this.run (() -> IntakeIn());
    }

    public Command HopperInCmd () {
        return this.run (() -> HopperIn());
    }

    public Command TransitionInCmd () {
        if (beambreakback()) {
            return this.run (() -> HopperIntakeIn());
        } else {
            return this.run (() -> JustIntake());
        }
    }

    public Command TransitionOutCmd () {
        if (!beambreakback()) {
            return this.run(() -> JustHopper());
        } else {
            return this.run (() -> HopperIntakeIn());
        }
    }

    public Command HopperEndCmd () {
        if (beambreakfront()) {
            return this.run (() -> StopMotors());
        } else {
            return this.run (() -> JustHopper());
        }
    }






    public Command IntakeOutCmd() {
        return this.run (() -> IntakeOut());
    }

    public Command HopperOutCmd () {
        return this.run (() -> HopperOut());
    }


    public void IntakeIn(){
        intakeLeader.set(Constants.HopperIntakeConstants.INTAKERUN);
        intakeFollower.set(Constants.HopperIntakeConstants.INTAKERUN);
    }

    public void IntakeOut(){
        intakeLeader.set(-Constants.HopperIntakeConstants.INTAKERUN);
        intakeFollower.set(-Constants.HopperIntakeConstants.INTAKERUN);
    }

    public void HopperIn() {
        hopperLeader.set(Constants.HopperIntakeConstants.INTAKERUN);
        hopperFollower.set(Constants.HopperIntakeConstants.INTAKERUN);
    }

    public void HopperOut() {
        hopperLeader.set(-Constants.HopperIntakeConstants.INTAKERUN);
        hopperFollower.set(-Constants.HopperIntakeConstants.INTAKERUN);
    }


    public void HopperIntakeIn () {
        intakeLeader.set(Constants.HopperIntakeConstants.INTAKERUN);
        intakeFollower.set(Constants.HopperIntakeConstants.INTAKERUN);
        hopperLeader.set(Constants.HopperIntakeConstants.INTAKERUN);
        hopperFollower.set(Constants.HopperIntakeConstants.INTAKERUN);

    }

    public void JustIntake () {
        intakeLeader.set(Constants.HopperIntakeConstants.INTAKERUN);
        intakeFollower.set(Constants.HopperIntakeConstants.INTAKERUN);
        intakeLeader.set(0);
        intakeFollower.set(0);
    }

    public void JustHopper () {
        intakeLeader.set(0);
        intakeFollower.set(0);
        hopperLeader.set(Constants.HopperIntakeConstants.INTAKERUN);
        hopperFollower.set(Constants.HopperIntakeConstants.INTAKERUN);
    }

    public void StopMotors () {
        intakeLeader.set(0);
        intakeFollower.set(0);
        intakeLeader.set(0);
        intakeFollower.set(0);
    }

    public  Command IntakeStopMotorsCmd () {

        return this.run (() -> IntakeStopMotors());
   
       }

    public  Command HopperStopMotorsCmd () {

        return this.run (() -> HopperStopMotors());
   
       }
 
    public boolean beambreakback () {
        return !digiInputBack.get();
      }
  
      public boolean beambreakfront () {
        return !digiInputFront.get();
      }
  
      public void IntakeStopMotors () {
        intakeLeader.set(0);
        intakeFollower.set(0);
  
      }

      public void HopperStopMotors () {
        hopperLeader.set(0);
        hopperFollower.set(0);
  
      }


}
