package frc.team3128.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3128.Constants;
import frc.team3128.common.NAR_EMotor;
import frc.team3128.common.hardware.motor.NAR_TalonSRX;

public class Climber extends SubsystemBase {

    private static Climber instance;
    
    private NAR_TalonSRX m_climbMotorD1, m_climbMotorD2, m_climbMotorS1, m_climbMotorS2;

    public Climber() {
        configMotors();
    }

    public static synchronized Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }

    private void configMotors() {
        m_climbMotorD1 = new NAR_TalonSRX(Constants.ClimberConstants.CLIMBER_D_MOTOR_1_ID);
        m_climbMotorD2 = new NAR_TalonSRX(Constants.ClimberConstants.CLIMBER_D_MOTOR_2_ID);
        m_climbMotorS1 = new NAR_TalonSRX(Constants.ClimberConstants.CLIMBER_S_MOTOR_1_ID);
        m_climbMotorS2 = new NAR_TalonSRX(Constants.ClimberConstants.CLIMBER_S_MOTOR_2_ID);
        m_climbMotorD2.follow((NAR_EMotor)m_climbMotorD1);
        m_climbMotorS2.follow((NAR_EMotor)m_climbMotorS1);
        m_climbMotorD1.setNeutralMode(Constants.ClimberConstants.CLIMBER_NEUTRAL_MODE);
        m_climbMotorS1.setNeutralMode(Constants.ClimberConstants.CLIMBER_NEUTRAL_MODE);
        
    }
    
    public void moveStraightClimberUp() {
        //If ControlMode is not specified, WPI_TalonSRX will default to PercentOutput
        m_climbMotorS1.set(Constants.ClimberConstants.CLIMBER_POWER);
    }
    public void moveDiagonalClimberUp() {
        //If ControlMode is not specified, WPI_TalonSRX will default to PercentOutput
        m_climbMotorD1.set(Constants.ClimberConstants.CLIMBER_POWER);
    }

    public void moveStraightClimberDown() {
        m_climbMotorD1.set(-Constants.ClimberConstants.CLIMBER_POWER);
    }
    public void moveDiagonalClimberDown() {
        m_climbMotorD1.set(-Constants.ClimberConstants.CLIMBER_POWER);
    }        
    public void stopDiagonalClimber() {
        m_climbMotorD1.set(0);
    }
    public void stopStraightClimber() {
        m_climbMotorS1.set(0);
    }

}