package frc.robot.subsystems.example;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public interface ExampleSubsystemIO {

    // The inputs of the subsystem. This is to be used only in the subsystem classes.
    ExampleSubsystemInputsAutoLogged inputs = new ExampleSubsystemInputsAutoLogged();

    /**
     * Set the position of the subsystem
     *
     * @param position The position of the subsystem. [units]
     */
    void setPosition(double position);

    /**
     * Set the power of the subsystem
     *
     * @param power The power of the subsystem. [-1, 1]
     */
    void setPower(double power);

    /**
     * Update the inputs of the subsystem
     */
    void updateInputs();

    class ExampleSubsystemInputs implements LoggableInputs {
        Rotation2d angle = new Rotation2d();
        Translation2d tipPosition = new Translation2d();
        Rotation2d velocity = new Rotation2d();

        @Override
        public void toLog(LogTable table) {
            table.put("Angle", angle.getDegrees());
            table.put("TipPosition", Translation2d.struct, tipPosition);
            table.put("Velocity", velocity.getDegrees());
        }

        @Override
        public void fromLog(LogTable table) {
            angle = Rotation2d.fromDegrees(table.get("Angle", angle.getDegrees()));
            tipPosition = table.get("TipPosition", Translation2d.struct, tipPosition);
            velocity = Rotation2d.fromDegrees(table.get("Velocity", velocity.getDegrees()));
        }
    }
}
