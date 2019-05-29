package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Drive;

@TeleOp(name="Test Drive", group="test")
public class TestDrive extends LinearOpMode {
    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, elapsedTime);
        Drive drive = new Drive(utilities);


        waitForStart();
        elapsedTime.reset();

//        for (int i = 0; i < 4 && opModeIsActive(); i++) {
//            drive.forward(18, 0.5f);
//            // drive.forward(9, 0.5f);
//            // drive.backward(9, 0.5f);
//            drive.backward(18, 0.5f);
//        }

        for (int i = 1; i <= 16; i += 2) {
            drive.turnLeft(-45 * i);
            drive.turnRight(-45 * (i + 1));
        }
    }
}
