package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import org.littletonrobotics.junction.Logger;

public class Arm extends SubsystemBase {

    private static Arm INSTANCE = null;

    private static final ArmInputsAutoLogged inputs = new ArmInputsAutoLogged();
    private final ArmIO io;

    private final ArmKinematics kinematics =
            new ArmKinematics(ArmConstants.SHOULDER_LENGTH, ArmConstants.ELBOW_LENGTH);

    private final Mechanism2d mechanism = new Mechanism2d(
            3, 3
    );
    private final MechanismRoot2d root = mechanism.getRoot("Arm", 1, 1);
    private final MechanismLigament2d shoulder = root.append(
            new MechanismLigament2d("Shoulder", ArmConstants.SHOULDER_LENGTH, 0)
    );
    private final MechanismLigament2d elbow = shoulder.append(
            new MechanismLigament2d("Elbow", ArmConstants.ELBOW_LENGTH, 0, 10, new Color8Bit(Color.kPurple))
    );

    public Arm(ArmIO io) {
        this.io = io;
    }

    public static Arm getInstance() {
        if (INSTANCE == null) {
            if (Robot.isReal()) {
                INSTANCE = new Arm(new ArmIOReal(inputs));
            } else {
                INSTANCE = new Arm(new ArmIOSim());
            }
        }
        return INSTANCE;
    }

    public ArmIO.ArmInputs getInputs() {
        return inputs;
    }

    public void setShoulderPower(double power) {
        inputs.shoulderPowerSetpoint = power;
        inputs.shoulderMode = ArmIO.Mode.OPEN_LOOP;
    }

    public void setElbowPower(double power) {
        inputs.elbowPowerSetpoint = power;
        inputs.elbowMode = ArmIO.Mode.OPEN_LOOP;
    }

    public void setShoulderAngle(double angle) {
        inputs.shoulderAngleSetpoint = angle;
        inputs.shoulderMode = ArmIO.Mode.CLOSED_LOOP;
    }

    public void setElbowAngle(double angle) {
        inputs.elbowAngleSetpoint = angle;
        inputs.elbowMode = ArmIO.Mode.CLOSED_LOOP;
    }

    public void setPosition(double x, double y) {
        var angles = kinematics.inverseKinematics(x, y);
        setShoulderAngle(angles.shoulderAngle);
        setElbowAngle(angles.elbowAngle);
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Arm", inputs);

        shoulder.setAngle(Math.toDegrees(inputs.shoulderAngle));
        elbow.setAngle(Math.toDegrees(inputs.elbowAngle - inputs.shoulderAngle));
        SmartDashboard.putData("Arm Mechanism", mechanism);

        if (inputs.shoulderMode.isClosedLoop) {
            io.setShoulderAngle(inputs.shoulderAngleSetpoint);
        } else {
            io.setShoulderPower(inputs.shoulderPowerSetpoint);
        }
        if (inputs.elbowMode.isClosedLoop) {
            io.setElbowAngle(inputs.elbowAngleSetpoint);
        } else {
            io.setElbowPower(inputs.elbowPowerSetpoint);
        }
    }
}
