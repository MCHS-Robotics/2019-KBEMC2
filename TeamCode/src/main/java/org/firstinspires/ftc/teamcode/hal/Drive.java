package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utilities;

import java.util.Stack;

public class Drive {
    private DcMotor left;
    private DcMotor right;
    private IMU imu;
    private Utilities utilities;

    private static final int ENCODERS_IN_ONE_ROTATION = 1120;
    private static final float WHEEL_RADIUS = 2;
    private static final float TURN_RADIUS = 10;

    private Stack<Command> shortTermMemory;
    private boolean reversing;

    public Drive(Utilities utilities) {
        this.left = utilities.getHardwareMap().get(DcMotor.class, "left");
        this.right = utilities.getHardwareMap().get(DcMotor.class, "right");
        this.utilities = utilities;
        setZeroPowerBehavior();
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        shortTermMemory = new Stack<>();
        reversing = false;
        imu = new IMU(utilities);
    }

    public void forward(float inches, double speed) {
        resetEncoders();
        setToPosition();
        left.setTargetPosition((int) Math.round(inches * ENCODERS_IN_ONE_ROTATION / (2 * Math.PI) / WHEEL_RADIUS));
        right.setTargetPosition((int) Math.round(inches * ENCODERS_IN_ONE_ROTATION / (2 * Math.PI) / WHEEL_RADIUS));
        left.setPower(speed);
        right.setPower(speed);
        while (utilities.getOpMode().opModeIsActive() && left.isBusy() && right.isBusy()) {
//            utilities.getTelemetry().addData("RotationL",left.getCurrentPosition());
//            utilities.getTelemetry().addData("RotationR",right.getCurrentPosition());
//            utilities.getTelemetry().update();
        }
        left.setPower(0);
        right.setPower(0);
        resetEncoders();

        if (!reversing) {
            shortTermMemory.push(new Command(Type.FORWARD, inches, this));
        }

        utilities.wait_(100);
    }

    public void backward(float inches, double speed) {
        forward(-inches,speed);
    }


    public void turnLeft(float degrees) {
        if (degrees < 0) {
            turnLeft(-degrees);
            return;
        }

        float start = imu.getRotation();
        float finalAng = start - degrees;

        imu.resetElapsedTime();

        right.setPower(0.4f);
        left.setPower(-0.4f);

        while (utilities.getOpMode().opModeIsActive() && imu.getRotation() > finalAng) {
            imu.update();
            utilities.getTelemetry().addData("Rotation", imu.getRotation());
            utilities.getTelemetry().update();
        }

//        resetEncoders();
//        setToPosition();
//        left.setTargetPosition((int) Math.round(degrees * ENCODERS_IN_ONE_ROTATION / (2 * Math.PI) / WHEEL_RADIUS) * 2 / 15);
//        right.setTargetPosition(-left.getTargetPosition());
//        left.setPower(0.3f);
//        right.setPower(0.3f);
//        while (utilities.getOpMode().opModeIsActive() && left.isBusy() && right.isBusy()) {
////            utilities.getTelemetry().addData("RotationL",left.getCurrentPosition());
////            utilities.getTelemetry().addData("RotationR",right.getCurrentPosition());
////            utilities.getTelemetry().addData("TargetL", left.getTargetPosition());
////            utilities.getTelemetry().addData("TargetR", right.getTargetPosition());
////            utilities.getTelemetry().update();
//        }
//        left.setPower(0);
//        right.setPower(0);
//        resetEncoders();

        right.setPower(0);
        left.setPower(0);

        if (!reversing) {
            shortTermMemory.push(new Command(Type.TURN_RIGHT, degrees, this));
        }

        utilities.wait_(100);
    }

    public void turnRight(float degrees) {
        if (degrees < 0) {
            turnRight(-degrees);
            return;
        }

        float start = imu.getRotation();
        float finalAng = start + degrees;

        imu.resetElapsedTime();

        right.setPower(-0.5f);
        left.setPower(0.5f);
        while (utilities.getOpMode().opModeIsActive() && imu.getRotation() < finalAng) {
            imu.update();
            utilities.getTelemetry().addData("Rotation", imu.getRotation());
            utilities.getTelemetry().update();
        }

        right.setPower(0);
        left.setPower(0);

        if (!reversing) {
            shortTermMemory.push(new Command(Type.TURN_RIGHT, degrees, this));
        }

        utilities.wait_(100);
    }

    public void reverse() {
        reversing = true;
        while (!shortTermMemory.empty()) {
            shortTermMemory.pop().reverse();
        }
        reversing = false;
    }

    public void reset() {
        while (!shortTermMemory.empty()) {
            shortTermMemory.pop();
        }
    }

    private class Command {
        private Type type;
        private float value;
        private Drive drive;

        public Command(Type type, float value, Drive drive) {
            this.type = type;
            this.value = value;
            this.drive = drive;
        }

        public void reverse() {
            if (type == Type.FORWARD) {
                drive.forward(-value, 0.6f);
            } else if (type == Type.TURN_RIGHT) {
                drive.turnRight(-value);
            }
        }
    }

    private enum Type {
        FORWARD, TURN_RIGHT
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
