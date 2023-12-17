package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.simulation.XboxControllerSim;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.swerve.SwerveDrive;
import frc.robot.swerve.commands.KeyboardDriveSim;

public class RobotContainer {

    private static RobotContainer INSTANCE = null;

    static {
        SwerveDrive.setInstance(Robot.isReal(),
                Ports.SwerveDrive.DRIVE_IDS,
                Ports.SwerveDrive.ANGLE_IDS,
                Ports.SwerveDrive.ENCODER_IDS
        );
    }

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        // Configure the button bindings and default commands
        configureDefaultCommands();
        configureButtonBindings();
    }

    public static RobotContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RobotContainer();
        }
        return INSTANCE;
    }

    private void configureDefaultCommands() {
        SwerveDrive.getInstance().setDefaultCommand(new KeyboardDriveSim());
    }

    private void configureButtonBindings() {
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
