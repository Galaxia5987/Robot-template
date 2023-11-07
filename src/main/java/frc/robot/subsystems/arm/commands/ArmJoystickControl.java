package frc.robot.subsystems.arm.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.common.utils.Utils;
import frc.robot.subsystems.arm.Arm;

public class ArmJoystickControl extends CommandBase {

    private final Arm arm = Arm.getInstance();

    private final XboxController xboxController;

    public ArmJoystickControl(XboxController xboxController) {
        this.xboxController = xboxController;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.setShoulderPower(Utils.deadband(-xboxController.getLeftY(), 0.2));
        arm.setElbowPower(Utils.deadband(-xboxController.getRightY(), 0.2));
    }
}
