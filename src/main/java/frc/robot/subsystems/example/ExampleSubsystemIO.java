package frc.robot.subsystems.example;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public interface ExampleSubsystemIO {

    // The inputs of the subsystem. This is to be used only in the subsystem classes.
    ExampleSubsystemInputs inputs = new ExampleSubsystemInputs();

    /**
     * Set the position of the subsystem
     *
     * @param angle The position of the subsystem.
     */
    void setAngle(Rotation2d angle);

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
        public Rotation2d angle = new Rotation2d();
        public Translation2d tipPosition = new Translation2d();
        public Rotation2d velocity = new Rotation2d();

        public Rotation2d setpointAngle = new Rotation2d();
        public double setpointPower = 0;

        @Override
        public void toLog(LogTable table) {
            table.put("Angle", angle.getDegrees());
            table.put("TipPosition", Translation2d.struct, tipPosition);
            table.put("Velocity", velocity.getDegrees());
            table.put("SetpointAngle", setpointAngle.getDegrees());
            table.put("SetpointPower", setpointPower);
        }

        @Override
        public void fromLog(LogTable table) {
            angle = Rotation2d.fromDegrees(table.get("Angle", angle.getDegrees()));
            tipPosition = table.get("TipPosition", Translation2d.struct, tipPosition);
            velocity = Rotation2d.fromDegrees(table.get("Velocity", velocity.getDegrees()));
            setpointAngle = Rotation2d.fromDegrees(table.get("SetpointAngle", setpointAngle.getDegrees()));
            setpointPower = table.get("SetpointPower", setpointPower);
        }
    }
}
