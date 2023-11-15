package frc.robot.subsystems.example;

import org.littletonrobotics.junction.AutoLog;

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
     * Update the inputs of the subsystem
     */
    void updateInputs();

    @AutoLog
    class ExampleSubsystemInputs {
        double input1 = 0;
        long input2 = 0;
        double[] input3 = new double[5];
        double input4 = 0;
    }
}
