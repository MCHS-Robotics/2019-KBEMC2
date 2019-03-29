package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive {
    private DcMotor left;
    private DcMotor right;
    private LinearOpMode opMode;

    private static final int ENCODERS_IN_ONE_ROTATION = 1120;
    private static final float WHEEL_RADIUS = 4;

    public Drive(HardwareMap hardwareMap, LinearOpMode opMode) {
        this.left = hardwareMap.get(DcMotor.class, "left");
        this.right = hardwareMap.get(DcMotor.class, "right");
        this.opMode = opMode;
        setZeroPowerBehavior();
    }

    public void forward(float inches) {
        resetEncoders();
        setToPosition();

        while (opMode.opModeIsActive()) {

        }

        resetEncoders();
    }

    public void backward(float inches) {
        forward(-inches);
    }

    public void turnRight(float degrees) {

    }

    public void turnLeft(float degrees) {
        turnRight(-degrees);
    }

    private void setZeroPowerBehavior() {
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setToPosition() {
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void resetEncoders() {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
