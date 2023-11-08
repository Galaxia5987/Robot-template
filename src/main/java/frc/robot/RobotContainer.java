package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.commands.ArmJoystickControl;
import frc.robot.subsystems.arm.commands.SetArmPosition;
import frc.robot.subsystems.example.ExampleSubsystem;

public class RobotContainer {

    private static RobotContainer INSTANCE = null;

    private final ExampleSubsystem exampleSubsystem = ExampleSubsystem.getINSTANCE();
    private final Arm arm = Arm.getInstance();

    private final XboxController xboxController = new XboxController(0);
    private final JoystickButton a = new JoystickButton(xboxController, XboxController.Button.kA.value);
    private final JoystickButton b = new JoystickButton(xboxController, XboxController.Button.kB.value);

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
        arm.setDefaultCommand(new ArmJoystickControl(xboxController));
    }

    private void configureButtonBindings() {
        a.whileTrue(new SetArmPosition(new Translation2d(0.6, 0)));
        b.whileTrue(new SetArmPosition(new Translation2d(-0.3, 0.4)));
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
