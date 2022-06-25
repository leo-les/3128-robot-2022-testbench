package frc.team3128;

import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.numbers.N2;

public class Constants {

    public static class ClimberConstants {
        public static final int CLIMBER_D_MOTOR_1_ID = 1;
        public static final int CLIMBER_D_MOTOR_2_ID = 2;
        public static final int CLIMBER_S_MOTOR_1_ID = 3;
        public static final int CLIMBER_S_MOTOR_2_ID = 4;
        public static final NeutralMode CLIMBER_NEUTRAL_MODE = null;
        public static final double CLIMBER_POWER = .5;


    }
    public static class ConversionConstants {
        public static final double FALCON_ENCODER_RESOLUTION = 2048;
        public static final double SPARK_ENCODER_RESOLUTION = 42;
        public static final double FALCON_NUp100MS_TO_RPM = 10 * 60 / FALCON_ENCODER_RESOLUTION; // (sensor units per 100 ms to rpm)

    }

    public static class DriveConstants {

        public static final int DRIVE_MOTOR_LEFT_LEADER_ID = 0;
        public static final int DRIVE_MOTOR_LEFT_FOLLOWER_ID = 1;
        public static final int DRIVE_MOTOR_RIGHT_LEADER_ID = 2;
        public static final int DRIVE_MOTOR_RIGHT_FOLLOWER_ID = 3;

        // Sim constants, TODO: move to new class

        // TODO: Get actual kv, ka
        public static final DCMotor GEARBOX = DCMotor.getFalcon500(4); 
        public static final LinearSystem<N2, N2, N2> DRIVE_CHAR = 
        LinearSystemId.identifyDrivetrainSystem(
            5,              // kvVoltSecondsPerMeter
            0.5,            // kaVoltSecondsSquaredPerMeter
            5,              // kvVoltSecondsPerRadian
            0.5             // kaVoltSecondsSquaredPerRadian
        );
        public static final double DRIVE_GEARING = 8;
        public static final double WHEEL_RADIUS_METERS = 0.0508; 
        public static final double TRACK_WIDTH_METERS = 0.66;        
        public static final double ENCODER_DISTANCE_PER_MARK = WHEEL_RADIUS_METERS * 2 / ConversionConstants.FALCON_ENCODER_RESOLUTION;

        public static final Boolean GYRO_REVERSED = false;
    }

    public static class VisionContants {

        public static final String TOP_HOSTNAME = "limelight-sog";

        public static final int SAMPLE_RATE = 3;

        public static final double TOP_CAMERA_ANGLE = -26.0; //degrees
        public static final double TOP_CAMERA_HEIGHT = 0.0; // Daniel - We had this at 0.0 previously, if we want to do more advanced math using vision this value should be measured - also determine units
        public static final double TOP_FRONT_DIST = 0.0; // Daniel - We had this at 0.0 previously, if we want to do more advanced math using vision this value should be measured.
        public static final double TARGET_WIDTH = 30.0; //inches

        public static final double VISION_PID_kP = 0.01;
        public static final double VISION_PID_kI = 0.02;
        public static final double VISION_PID_kD = 0.00006;

        public static final double TX_OFFSET = 0.0; // to offset alignment in either direction

        public static final double TX_THRESHOLD = 1; //degrees
        public static final double TX_THRESHOLD_MAX = 2; //degrees
        public static final double TIME_TO_MAX_THRESHOLD = 5; //seconds
        public static final double TX_THRESHOLD_INCREMENT = (TX_THRESHOLD_MAX - TX_THRESHOLD) / TIME_TO_MAX_THRESHOLD; //degrees per second

        public static final int ALIGN_PLATEAU_COUNT = 10; //Number of checks at correct RPM to shoot
        
    }
    public static class VisionConstants {

        public static final String TOP_HOSTNAME = "limelight-cog";

        public static final int SAMPLE_RATE = 3;

        public static final double TOP_CAMERA_ANGLE = (90 - 42.71) * Math.PI / 180; // radians
        public static final double TOP_CAMERA_HEIGHT = 23.5; // in 
        public static final double TOP_FRONT_DIST = 0;
        public static final double TARGET_HEIGHT = 104;

        public static final double VISION_PID_kP = 2.2e-3;
        public static final double VISION_PID_kI = 0; // 0.02;
        public static final double VISION_PID_kD = 0; // 0.00006;
        public static final double VISION_PID_kF = 0.06;

        public static final double TX_OFFSET = 0; // to offset alignment in either direction

        public static final double TX_THRESHOLD = 2; //degrees
        public static final double TX_THRESHOLD_MAX = 5; //degrees
        public static final double TIME_TO_MAX_THRESHOLD = 5; //seconds
        public static final double TX_THRESHOLD_INCREMENT = (TX_THRESHOLD_MAX - TX_THRESHOLD) / TIME_TO_MAX_THRESHOLD; //degrees per second

        public static final int ALIGN_PLATEAU_COUNT = 10; //Number of checks at correct RPM to shoot

        public static final double BALL_TARGET_HEIGHT = 9.5 / 2;
        public static final double BALL_LL_HEIGHT = 24;
        public static final double BALL_LL_ANGLE = 65.15 * Math.PI / 180; // 1.0; // Math.acos(21.0 / 39.0); // 1.002186; // radians
        public static final double BALL_LL_FRONT_DIST = 0; // meters, measure

        public static final double GOAL_HORIZONTAL_OFFSET = 0; // goal of x displacement from robot to ball/target - ideally 0 but if limelight not center change 
        public static final double BALL_THRESHOLD = 5;
        
        public static final double BALL_VISION_kF = 0.8;
        public static final double BALL_VISION_kP = 0.01;
        public static final double BALL_VISION_kD = 0.00001;
        public static final double BALL_AUTO_PURSUIT_kF = 0.4;

        public static final double BALL_DECELERATE_START_DISTANCE = 25; 
        public static final double BALL_DECELERATE_END_DISTANCE = 9.5; 

        public static final double BALL_VEL_THRESHOLD = 2.54; // m/s - 100 in/s 
        public static final int BALL_VEL_PLATEAU_THRESHOLD = 10;

        public static final double COLOR_SENSOR_TOLERANCE = 1.5;
        public static final int PROXIMITY_SENSOR_MAX = 2047;
        public static final double PROXIMITY_SENSOR_THRESHOLD = 100;

    }
}
