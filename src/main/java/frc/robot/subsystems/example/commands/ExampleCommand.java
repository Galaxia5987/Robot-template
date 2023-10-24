package frc.robot.subsystems.example.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.example.ExampleSubsystem;

public class ExampleCommand extends CommandBase {

    private final ExampleSubsystem exampleSubsystem = ExampleSubsystem.getINSTANCE();

    public ExampleCommand() {
    }

    @Override
    public void initialize() {
        /*
        What to do when the command starts
         */
    }

    @Override
    public void execute() {
        /*
        What to do while the command is running
         */
    }

    @Override
    public void end(boolean interrupted) {
        /*
        What to do when the command ends
         */
    }

    @Override
    public boolean isFinished() {
        /*
        Whether the command is finished
         */
        return false;
    }
}
