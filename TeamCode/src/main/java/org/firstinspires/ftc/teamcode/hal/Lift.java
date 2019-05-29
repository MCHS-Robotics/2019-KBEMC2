package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utilities;

public class Lift {
    private static final int ENCODERS_IN_ONE_ROTATION = 1120 / 2;
    private static final float LEAD_SCREW_PITCH = 2; // millimeters
    private static final float LEAD_SCREW_RADIUS = 4; // millimeters
    private static final float LEAD_SCREW_LENGTH = 360; // millimeters
    private static final float LEAD_SCREW_TRAVEL_LENGTH = 12; // inches

    private DcMotor lift;
    private Utilities utilities;

    public Lift(Utilities utilities) {
        lift = utilities.getHardwareMap().get(DcMotor.class, "lift");
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.utilities = utilities;
    }

    public void up() {
        new Thread(new Runnable() {
            @Override
            public void run() {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            lift.setTargetPosition((int) (1778 * LEAD_SCREW_TRAVEL_LENGTH));
            lift.setPower(0.8f);

            while (utilities.getOpMode().opModeIsActive() && lift.isBusy());

            lift.setPower(0);

            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }).start();
    }

    public void down() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                lift.setTargetPosition((int) (-1778 * LEAD_SCREW_TRAVEL_LENGTH));
                lift.setPower(0.8f);

                while (utilities.getOpMode().opModeIsActive() && lift.isBusy());

                lift.setPower(0);

                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }).start();
    }
}
