package frc.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ArmIOSim implements ArmIO {

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

    private final ArmInputs inputs;

    public ArmIOSim(ArmInputs inputs) {
        this.inputs = inputs;
    }

    @Override
    public void setShoulderAngle(double angle) {
        inputs.shoulderAppliedVoltage = shoulderController.calculate(inputs.shoulderAngle, angle);
        shoulderMotor.setInputVoltage(inputs.shoulderAppliedVoltage);
    }

    @Override
    public void setElbowAngle(double angle) {
        inputs.elbowAppliedVoltage = elbowController.calculate(inputs.elbowAngle, angle);
        elbowMotor.setInputVoltage(inputs.elbowAppliedVoltage);
    }

    @Override
    public void setShoulderPower(double power) {
        inputs.shoulderAppliedVoltage = power * 12;
        shoulderMotor.setInputVoltage(inputs.shoulderAppliedVoltage);
    }

    @Override
    public void setElbowPower(double power) {
        inputs.elbowAppliedVoltage = power * 12;
        elbowMotor.setInputVoltage(inputs.elbowAppliedVoltage);
    }

    @Override
    public void updateInputs() {
        shoulderMotor.update(0.02);
        elbowMotor.update(0.02);

        inputs.shoulderAngle = shoulderMotor.getAngleRads();
        inputs.shoulderAppliedCurrent = shoulderMotor.getCurrentDrawAmps();

        inputs.elbowAngle = elbowMotor.getAngleRads();
        inputs.elbowAppliedCurrent = elbowMotor.getCurrentDrawAmps();
    }
}
