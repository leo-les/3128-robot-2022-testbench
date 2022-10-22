package frc.team3128.subsystems; 

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3128.common.hardware.PicoColorSensor;
import frc.team3128.common.hardware.PicoColorSensor.RawColor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation; 
import static frc.team3128.Constants.ColorSensorConstants.*;

public class ColorSensor extends SubsystemBase {

    private static ColorSensor instance;

    private PicoColorSensor m_colorSensor;
    private DigitalInput m_bottomDistanceSensor;
    private DigitalInput m_topDistanceSensor;

    public ColorSensor() {
        m_colorSensor = new PicoColorSensor();
        m_bottomDistanceSensor = new DigitalInput(0);
        m_topDistanceSensor = new DigitalInput(1);
    }

    public static synchronized ColorSensor getInstance() {
        if (instance == null) 
            instance = new ColorSensor();
        return instance;
    }

    public boolean getBallBottomLocation() {
        return m_bottomDistanceSensor.get();
    }

    public boolean getBallTopLocation() {
        return m_topDistanceSensor.get();
    }

    /**
     * Returns true if bottom ball is wrong color, false if ball is right color or missing
     */
    public boolean getWrongBallBottom() {
        RawColor color = m_colorSensor.getRawColor0();
        if (color.red > color.blue*COLOR_SENSOR_TOLERANCE) {
            //return if red
            return DriverStation.getAlliance() != DriverStation.Alliance.Red;
        } else if (color.blue > color.red*COLOR_SENSOR_TOLERANCE) {
            //return if blue
            return DriverStation.getAlliance() != DriverStation.Alliance.Blue;
        } 
        return false;
    }

    /**
     * Returns true if top ball is wrong color, false if ball is right color or missing
     */
    public boolean getWrongBallTop() {
        RawColor color = m_colorSensor.getRawColor1();
        if (color.red > color.blue*1.5) {
            //return if red
            return DriverStation.getAlliance() != DriverStation.Alliance.Red;
        } else if (color.blue > color.red*1.5) {
            //return if blue
            return DriverStation.getAlliance() != DriverStation.Alliance.Blue;
        }
        return false;
    }
}