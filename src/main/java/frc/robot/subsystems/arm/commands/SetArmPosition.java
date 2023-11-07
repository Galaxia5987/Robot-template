package frc.robot.subsystems.arm.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.Arm;

public class SetArmPosition extends CommandBase {

    private final Arm arm = Arm.getInstance();

    private final Translation2d position;

    public SetArmPosition(Translation2d position) {
        this.position = position;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.setPosition(position.getX(), position.getY());
    }
}
