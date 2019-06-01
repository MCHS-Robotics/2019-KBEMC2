package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.NormalDriveIMU;

@TeleOp(name="Test IMU Turning", group="test")
public class TestIMUTurning extends LinearOpMode {
    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, Utilities.Side.BLUE, elapsedTime);
        NormalDriveIMU drive = new NormalDriveIMU(
                hardwareMap.get(DcMotor.class, "left"),
                hardwareMap.get(DcMotor.class, "right"),
                telemetry,
                0.25f,
                hardwareMap);


        waitForStart();
        elapsedTime.reset();

        for (int i = 1; i <= 8; i++) {
            drive.pivotLeft(45 * i);

            utilities.wait_(1000);

            drive.pivotRight(45 * i);

            utilities.wait_(1000);
        }
    }
}
