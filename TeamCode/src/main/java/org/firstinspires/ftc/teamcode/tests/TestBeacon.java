package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BeaconDeterminer;
import org.firstinspires.ftc.teamcode.Utilities;
import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;

@TeleOp(name="Test Beacon", group="test")
public class TestBeacon extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, Utilities.Side.BLUE, elapsedTime);


        ComputerVision computerVision = new ComputerVision(utilities);
        Collection collection = null;
        Drive drive = new Drive(utilities);
        // Lift lift = new Lift(utilities);

        BeaconDeterminer beaconDeterminer = new BeaconDeterminer(utilities);

        waitForStart();
        elapsedTime.reset();

        BeaconDeterminer.BeaconState beaconState = beaconDeterminer.determine();
        while (beaconState == BeaconDeterminer.BeaconState.CONFUSED) {
            beaconState = beaconDeterminer.determine();
        }
//
//        telemetry.addData("Beacon State", beaconState.toString());
//        telemetry.update();


    }
}
