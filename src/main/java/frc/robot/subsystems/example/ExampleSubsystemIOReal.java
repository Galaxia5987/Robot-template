package frc.robot.subsystems.example;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

public class ExampleSubsystemIOReal implements ExampleSubsystemIO {

    private final TalonFX motor = new TalonFX(ExampleSubsystemConstants.MOTOR_PORT);

    private final PositionVoltage positionRequest = new PositionVoltage(0);
    private final DutyCycleOut dutyCycleRequest = new DutyCycleOut(0);

    public ExampleSubsystemIOReal() {
        while (motor.getConfigurator().apply(ExampleSubsystemConstants.CONFIG) != StatusCode.OK) {
            System.out.println("Configuring motor " + motor.getDeviceID());
        }
    }

    @Override
    public void setPosition(double position) {
        positionRequest.withPosition(position).withEnableFOC(true);
        motor.setControl(positionRequest);
    }

    @Override
    public void setPower(double power) {
        dutyCycleRequest.withOutput(power).withEnableFOC(true);
        motor.setControl(dutyCycleRequest);
    }

    @Override
    public void updateInputs() {

    }
}
