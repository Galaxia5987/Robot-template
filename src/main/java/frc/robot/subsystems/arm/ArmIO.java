package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {

    default void setShoulderAngle(double angle) {}

    default void setElbowAngle(double angle) {}

    default void setShoulderPower(double power) {}

    default void setElbowPower(double power) {}

    default void updateInputs(ArmInputs inputs) {}

    enum Mode {
        OPEN_LOOP(false),
        CLOSED_LOOP(true);

        public final boolean isClosedLoop;

        Mode(boolean isClosedLoop) {
            this.isClosedLoop = isClosedLoop;
        }

        public static Mode of(boolean isClosedLoop) {
            return isClosedLoop ? CLOSED_LOOP : OPEN_LOOP;
        }
    }

    @AutoLog
    class ArmInputs {
        public double shoulderAngle = 0;
        public double shoulderAppliedVoltage = 0;
        public double shoulderAppliedCurrent = 0;

        public double shoulderAngleSetpoint = 0;
        public double shoulderPowerSetpoint = 0;
        public Mode shoulderMode = Mode.OPEN_LOOP;

        public double elbowAngle = 0;
        public double elbowAppliedVoltage = 0;
        public double elbowAppliedCurrent = 0;

        public double elbowAngleSetpoint = 0;
        public double elbowPowerSetpoint = 0;
        public Mode elbowMode = Mode.OPEN_LOOP;
    }
}
