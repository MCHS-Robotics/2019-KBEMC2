package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utilities;

public class Lift {
    private static final int ENCODERS_IN_ONE_ROTATION = 1120 / 2;
    private static final float LEAD_SCREW_PITCH = 2; // millimeters
    private static final float LEAD_SCREW_RADIUS = 4; // millimeters
    private static final float LEAD_SCREW_LENGTH = 360; // millimeters
    private static final float LEAD_SCREW_TRAVEL_LENGTH = LEAD_SCREW_LENGTH - 10; // millimeters

    private DcMotor lift;

    public Lift(Utilities utilities) {
        lift = utilities.getHardwareMap().get(DcMotor.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up() {
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setTargetPosition((int) (1778 * LEAD_SCREW_TRAVEL_LENGTH));
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void down() {
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setTargetPosition((int) (-1778 * LEAD_SCREW_TRAVEL_LENGTH));

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
