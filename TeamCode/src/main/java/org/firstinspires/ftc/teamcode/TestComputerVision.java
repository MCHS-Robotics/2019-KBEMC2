package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BeaconDeterminer;
import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Camera;
import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.Lift;

@TeleOp(name="Test Computer Vision", group="test")
public class TestComputerVision extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, elapsedTime);


        ComputerVision computerVision = new ComputerVision(utilities);
        // Collection collection = new Collection(utilities);
        Drive drive = new Drive(utilities);
        // Lift lift = new Lift(utilities);

        BeaconDeterminer beaconDeterminer = new BeaconDeterminer();

        waitForStart();
        elapsedTime.reset();

        while (opModeIsActive()) {
            float angle = computerVision.getHorizontalAngle();
            telemetry.addData("Theta", angle);
            telemetry.update();
        }
    }
}
