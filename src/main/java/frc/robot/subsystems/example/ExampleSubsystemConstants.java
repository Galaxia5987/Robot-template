package frc.robot.subsystems.example;

import com.ctre.phoenix6.configs.ClosedLoopGeneralConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ExampleSubsystemConstants {

    public static final double CURRENT_LIMIT = 40; // [amp]

    public static final double POSITION_P = 0.1;
    public static final double POSITION_I = 0.0;
    public static final double POSITION_D = 0.0;
    public static final double POSITION_V = 0.0;

    public static final double GEAR_RATIO = (32.0 / 15.0) * (20.0 / 15.0);

    public static final TalonFXConfiguration CONFIG = new TalonFXConfiguration();
    static {
        CONFIG.Slot0.withKP(POSITION_P).withKI(POSITION_I).withKD(POSITION_D).withKV(POSITION_V);
        CONFIG.CurrentLimits.withStatorCurrentLimit(40)
                .withSupplyCurrentLimit(40)
                .withStatorCurrentLimitEnable(true)
                .withSupplyCurrentLimitEnable(true)
                .withSupplyTimeThreshold(0);
        CONFIG.Feedback.withSensorToMechanismRatio(GEAR_RATIO);
    }

    public static final int MOTOR_PORT = 1;
}
