package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;

@TeleOp(name="Test Reverse", group="test")
public class TestReverse extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, Utilities.Side.BLUE, elapsedTime);


        ComputerVision computerVision = new ComputerVision(utilities);
        Collection collection = null;
        Drive drive = new Drive(utilities);
        // Lift lift = new Lift(utilities);

        waitForStart();
        elapsedTime.reset();

        drive.forward(15, 0.5f);
        drive.turnRight(90);
        drive.forward(12, 0.5f);
        drive.reverse();
    }
}
