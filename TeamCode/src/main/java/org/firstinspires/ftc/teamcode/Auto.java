package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hal.AutoRelease;
import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.Lift;

public abstract class Auto extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    protected abstract Utilities.Side getSide();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, getSide(), elapsedTime);

        ComputerVision computerVision = new ComputerVision(utilities);
        Collection collection = new Collection(utilities);
        Drive drive = new Drive(utilities);
        Lift lift = new Lift(utilities);
        BeaconDeterminer beaconDeterminer = new BeaconDeterminer(utilities);
        AutoRelease autoRelease = new AutoRelease(utilities);

        waitForStart();
        elapsedTime.reset();

        telemetry.addData("Message", "Code has begun executing!");
        telemetry.update();

        lift.up(3);
        drive.forward(80,0.4f);
        lift.down(3);
        drive.turnLeft(90);
        drive.forward(5,1.0f);
        lift.up();
        drive.forward(60, 1.0f);
        drive.turnRight(90);
        drive.forward(42,1.0f);
        drive.turnRight(90);

        BeaconDeterminer.BeaconState beaconState = BeaconDeterminer.BeaconState.CONFUSED;
        while (beaconState == BeaconDeterminer.BeaconState.CONFUSED && opModeIsActive()) {
            beaconState = beaconDeterminer.determine();
            drive.forward(1.5f, 1.0f);
        }
        if (beaconState == BeaconDeterminer.BeaconState.LEFT) {
            drive.backward(22, 1.0f);
            autoRelease.release();
            drive.turnLeft(90);
        } else if (beaconState == BeaconDeterminer.BeaconState.RIGHT) {
            drive.forward(25, 1.0f);
            autoRelease.release();
            drive.turnLeft(90);
        }

        autoRelease.reset();
        drive.forward(6, 1.0f);

        while (opModeIsActive());
    }
}
