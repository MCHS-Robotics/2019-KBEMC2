package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Drive;

@TeleOp(name="Test IMU Turning", group="test")
public class TestIMUTurning extends LinearOpMode {
    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, Utilities.Side.BLUE, elapsedTime);
        Drive drive = new Drive(utilities);


        waitForStart();
        elapsedTime.reset();

        for (int i = 1; i <= 8; i++) {
            drive.turnLeft(45 * i);

            utilities.wait_(1000);

            drive.turnRight(45 * i);

            utilities.wait_(1000);
        }
    }
}
