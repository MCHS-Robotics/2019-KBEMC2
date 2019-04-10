package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hal.Drive;

@Autonomous(name="DriveTest", group="test")
public class DriveTest extends LinearOpMode {
    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, elapsedTime);
        Drive drive = new Drive(utilities);

        waitForStart();
        elapsedTime.reset();

        for (int i = 0; i < 4; i++) {
            drive.forward(10);
            drive.turnRight(90);
        }
    }
}
