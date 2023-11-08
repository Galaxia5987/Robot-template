package frc.robot.subsystems.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ArmIOSim implements ArmIO {

    private double shoulderAppliedVoltage = 0;
    private double shoulderAngle = 0;
    private double elbowAppliedVoltage = 0;
    private double elbowAngle = 0;

    private final SingleJointedArmSim shoulderMotor = new SingleJointedArmSim(
            DCMotor.getFalcon500(2),
            ArmConstants.SHOULDER_GEARING,
            ArmConstants.SHOULDER_MOMENT_OF_INERTIA,
            ArmConstants.SHOULDER_LENGTH,
            0, 2 * Math.PI, false
    );
    private final PIDController shoulderController = new PIDController(2, 0, 0, 0.02);

    private final SingleJointedArmSim elbowMotor = new SingleJointedArmSim(
            DCMotor.getFalcon500(2),
            ArmConstants.ELBOW_GEARING,
            ArmConstants.ELBOW_MOMENT_OF_INERTIA,
            ArmConstants.ELBOW_LENGTH,
            -2 * Math.PI, 2 * Math.PI, false
    );
    private final PIDController elbowController = new PIDController(2, 0, 0, 0.02);

    public ArmIOSim() {
    }

    @Override
    public void setShoulderAngle(double angle) {
        shoulderAppliedVoltage = shoulderController.calculate(shoulderAngle, angle);
        shoulderMotor.setInputVoltage(shoulderAppliedVoltage);
    }

    @Override
    public void setElbowAngle(double angle) {
        elbowAppliedVoltage = elbowController.calculate(elbowAngle, angle);
        elbowMotor.setInputVoltage(elbowAppliedVoltage);
    }

    @Override
    public void setShoulderPower(double power) {
        shoulderAppliedVoltage = power * 12;
        shoulderMotor.setInputVoltage(shoulderAppliedVoltage);
    }

    @Override
    public void setElbowPower(double power) {
        elbowAppliedVoltage = power * 12;
        elbowMotor.setInputVoltage(elbowAppliedVoltage);
    }

    @Override
    public void updateInputs(ArmInputs inputs) {
        shoulderMotor.update(0.02);
        elbowMotor.update(0.02);

        inputs.shoulderAngle = shoulderMotor.getAngleRads();
        shoulderAngle = inputs.shoulderAngle;
        inputs.shoulderAppliedCurrent = shoulderMotor.getCurrentDrawAmps();
        inputs.shoulderAppliedVoltage = shoulderAppliedVoltage;

        inputs.elbowAngle = elbowMotor.getAngleRads();
        elbowAngle = inputs.elbowAngle;
        inputs.elbowAppliedCurrent = elbowMotor.getCurrentDrawAmps();
        inputs.elbowAppliedVoltage = elbowAppliedVoltage;
    }
}