package frc.team3128;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.team3128.commands.ArcadeDrive;
import frc.team3128.commands.TestDrive;
import frc.team3128.common.hardware.PicoColorSensor;
import frc.team3128.common.hardware.input.NAR_Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team3128.subsystems.ColorSensor;
// import frc.team3128.subsystems.Climber;
import frc.team3128.subsystems.NAR_Drivetrain;
import frc.team3128.subsystems.TestBenchSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    //private NAR_Drivetrain m_drive;
    private TestBenchSubsystem testBenchSubsystem;
    private NAR_Joystick m_leftStick;
    private NAR_Joystick m_rightStick;
    private PicoColorSensor colorSensor;
    private ColorSensor m_colorSensor;

    private Trigger isBallTop;
    private Trigger isBallBottom;
    private Trigger isBallWrongBottom;
    private Trigger isBallWrongTop;
    private Trigger isBallWrongBoth;
    private Trigger isBallWrongBottomAndTopCorrect;
    private Trigger isBallWrongTopAndBottomCorrect;
    private Trigger isBallWrongTopAndBottomMissing;

    private CommandScheduler m_commandScheduler = CommandScheduler.getInstance();
    // private Climber m_climber = Climber.getInstance();
    private Command auto;

    private boolean DEBUG = false;

    public RobotContainer() {

        //m_drive = NAR_Drivetrain.getInstance();

        //Enable all PIDSubsystems so that useOutput runs

        // m_leftStick = new NAR_Joystick(0);
        // m_rightStick = new NAR_Joystick(1);
        // testBenchSubsystem = new TestBenchSubsystem();
        //m_commandScheduler.setDefaultCommand(testBenchSubsystem, new TestDrive(testBenchSubsystem));
        m_colorSensor = ColorSensor.getInstance();
        colorSensor = new PicoColorSensor();
        configureButtonBindings();
        dashboardInit();

        //Basic color sensor triggers that sense a single ball position, used to form the more complicated triggers below
        isBallBottom = new Trigger(m_colorSensor::getBallBottomLocation);

        isBallTop = new Trigger(m_colorSensor::getBallTopLocation);

        isBallWrongBottom = new Trigger(m_colorSensor::getWrongBallBottom);

        isBallWrongTop = new Trigger(m_colorSensor::getWrongBallTop);

        //Color sensor triggers that actually trigger a response by the robot: a combination of the single triggers above
        isBallWrongBoth = isBallBottom
                            .and(isBallTop)
                            .and(isBallWrongBottom)
                            .and(isBallWrongTop);

        isBallWrongBottomAndTopCorrect = isBallBottom
                                .and(isBallTop)
                                .and(isBallWrongBottom)
                                .and(isBallWrongTop.negate());

        isBallWrongTopAndBottomCorrect = isBallBottom
                                            .and(isBallTop)
                                            .and(isBallWrongBottom.negate())
                                            .and(isBallWrongTop);

        isBallWrongTopAndBottomMissing = isBallBottom.negate()
                                            .and(isBallTop)
                                            .and(isBallWrongTop);

        
    }   

    private void configureButtonBindings() {
        //m_rightStick.getButton(1).whenActive(new RunCommand(testBenchSubsystem::drive,testBenchSubsystem));
        //m_rightStick.getButton(1).whenReleased(new RunCommand(testBenchSubsystem::stop,testBenchSubsystem));
    }

    private void dashboardInit() {
        if (DEBUG) {
            SmartDashboard.putData("CommandScheduler", CommandScheduler.getInstance());
            //SmartDashboard.putData("Drivetrain", m_drive);
        }
            
    }
    public void updateDashboard(){
        SmartDashboard.putBoolean("Bottom Location", isBallBottom.getAsBoolean());
        SmartDashboard.putBoolean("Top Location", isBallTop.getAsBoolean());
        SmartDashboard.putBoolean("Bottom Wrong", isBallWrongBottom.getAsBoolean());
        SmartDashboard.putBoolean("Top Wrong", isBallWrongTop.getAsBoolean());
        SmartDashboard.putBoolean("isBallWrongBoth", isBallWrongBoth.getAsBoolean());
        SmartDashboard.putBoolean("isBallWrongBottomAndTopCorrect", isBallWrongBottomAndTopCorrect.getAsBoolean());
        SmartDashboard.putBoolean("isBallWrongTopAndBottomCorrect", isBallWrongTopAndBottomCorrect.getAsBoolean());
        SmartDashboard.putBoolean("isBallWrongTopAndBottomMissing", isBallWrongTopAndBottomMissing.getAsBoolean());
        


    }

    public void stopDrivetrain() {
        //m_drive.stop();
    }

    public Command getAutonomousCommand() {
        return auto;
    }
}
