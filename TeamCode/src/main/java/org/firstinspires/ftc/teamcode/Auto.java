package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

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

        waitForStart();
        elapsedTime.reset();

        telemetry.addData("Message", "Code has begun executing!");
        telemetry.update();

        drive.forward(77,0.4f);
        lift.up();
        drive.turnLeft(90);
        drive.forward(57,1.0f);
        drive.turnRight(90);
        drive.forward(50,1.0f);
        drive.turnRight(90);

        BeaconDeterminer.BeaconState beaconState = BeaconDeterminer.BeaconState.CONFUSED;
        while (beaconState == BeaconDeterminer.BeaconState.CONFUSED && opModeIsActive()) {
            beaconState = beaconDeterminer.determine();
            drive.forward(1, 0.8f);
        }
        if (beaconState == BeaconDeterminer.BeaconState.LEFT) {
            drive.turnLeft(180);
            drive.forward(22, 1.0f);
            drive.turnRight(90);
        } else if (beaconState == BeaconDeterminer.BeaconState.RIGHT) {
            drive.forward(25, 1.0f);
            drive.turnLeft(90);
        }

        drive.forward(6, 1.0f);
        collection.release();
    }
}
