package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BallCollector;
import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;

@TeleOp(name="Test Computer Vision", group="test")
public class TestComputerVision extends LinearOpMode {

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

        BallCollector bc = new BallCollector(utilities, computerVision, drive, collection);

        while (opModeIsActive()) {
             drive.forward(5, 0.3f);
             bc.collect(false);
             drive.reverse();
        }
    }
}
