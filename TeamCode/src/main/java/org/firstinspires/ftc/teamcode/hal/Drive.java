package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Util;

import org.firstinspires.ftc.teamcode.Utilities;

public class Drive {
    private DcMotor left;
    private DcMotor right;
    private Utilities utilities;

    private static final int ENCODERS_IN_ONE_ROTATION = 1120;
    private static final float WHEEL_RADIUS = 4;

    public Drive(Utilities utilities) {
        this.left = utilities.getHardwareMap().get(DcMotor.class, "left");
        this.right = utilities.getHardwareMap().get(DcMotor.class, "right");
        this.utilities = utilities;
        setZeroPowerBehavior();
    }

    public void forward(float inches) {
        resetEncoders();
        setToPosition();

        while (utilities.getOpMode().opModeIsActive()) {

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
