package frc.team3128;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.team3128.commands.ArcadeDrive;
//import frc.team3128.commands.TestDrive;
import frc.team3128.common.hardware.input.NAR_Joystick;
import frc.team3128.subsystems.NAR_Drivetrain;
import frc.team3128.subsystems.TestBenchPiston;
import frc.team3128.subsystems.TestBenchMotor;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    //private NAR_Drivetrain m_drive;
    private TestBenchMotor testBenchMotor;
    private TestBenchPiston testBenchPiston;
    private NAR_Joystick m_leftStick;
    private NAR_Joystick m_rightStick;

    private CommandScheduler m_commandScheduler = CommandScheduler.getInstance();
    private Command auto;

    private boolean DEBUG = false;

    public RobotContainer() {

        //m_drive = NAR_Drivetrain.getInstance();

        //Enable all PIDSubsystems so that useOutput runs

        m_leftStick = new NAR_Joystick(0);
        m_rightStick = new NAR_Joystick(1);
        testBenchPiston = new TestBenchPiston();
        testBenchMotor = new TestBenchMotor(); 
        //m_commandScheduler.setDefaultCommand(testBenchSubsystem, new TestDrive(testBenchSubsystem));

        configureButtonBindings();
        dashboardInit();
    }   

    private void configureButtonBindings() {
        m_rightStick.getButton(1).whenActive(new RunCommand(testBenchMotor::run,testBenchMotor));
        m_rightStick.getButton(1).whenReleased(new RunCommand(testBenchMotor::stop,testBenchMotor));

        m_rightStick.getButton(8).whenActive(new RunCommand(testBenchPiston::eject,testBenchPiston));
        m_rightStick.getButton(8).whenReleased(new RunCommand(testBenchPiston::off,testBenchPiston));

        m_rightStick.getButton(10).whenActive(new RunCommand(testBenchPiston::retract,testBenchPiston)); 
        m_rightStick.getButton(10).whenReleased(new RunCommand(testBenchPiston::off,testBenchPiston));
    }

    private void dashboardInit() {
        if (DEBUG) {
            SmartDashboard.putData("CommandScheduler", CommandScheduler.getInstance());
            //SmartDashboard.putData("Drivetrain", m_drive);
        }
            
    }

    public void stopDrivetrain() {
        //m_drive.stop();
    }

    public Command getAutonomousCommand() {
        return auto;
    }
}
