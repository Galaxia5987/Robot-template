package frc.robot.subsystems.example;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import org.littletonrobotics.junction.Logger;

public class ExampleSubsystem extends SubsystemBase {

    // Single instance in all code of the subsystem
    private static ExampleSubsystem INSTANCE = null;

    // Logger inputs of the subsystem
    private final ExampleSubsystemInputsAutoLogged inputs = ExampleSubsystemIO.inputs;

    // IO of the subsystem
    private final ExampleSubsystemIO io;

    /**
     * Constructor for ExampleSubsystem.
     *
     * @param io IO of the subsystem.
     */
    private ExampleSubsystem(ExampleSubsystemIO io) {
        this.io = io;
    }

    /**
     * Gets the single instance of ExampleSubsystem.
     *
     * @return The single instance of ExampleSubsystem.
     */
    public static ExampleSubsystem getINSTANCE() {
        if (INSTANCE == null) {
            if (Robot.isReal()) {
                INSTANCE = new ExampleSubsystem(new ExampleSubsystemIOReal());
            } else {
                INSTANCE = new ExampleSubsystem(new ExampleSubsystemIOSim());
            }
        }
        return INSTANCE;
    }

    /**
     * Sets the position of the subsystem.
     *
     * @param position The position of the subsystem. [units]
     */
    public void setPosition(double position) {
        inputs.input4 = position;
    }

    /**
     * Get simple command. This command includes only this subsystem.
     *
     * @param position The position of the subsystem.
     * @return The simple command.
     */
    public Command getSimpleCommand(double position) {
        return new RunCommand(() -> setPosition(position), this);
    }

    /**
     * Updates the state of the subsystem.
     */
    @Override
    public void periodic() {
        // Update inputs from IO
        io.updateInputs();
        // Log inputs
        Logger.getInstance().processInputs("ExampleSubsystem", inputs);

        // Give set point to IO
        io.setPosition(inputs.input4);
    }
}
