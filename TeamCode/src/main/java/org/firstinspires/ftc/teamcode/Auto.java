package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hal.Camera;
import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.Lift;

@Autonomous(name="Auto", group="KBEMC2")
public class Auto extends LinearOpMode {

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap);

        Camera camera = new Camera();
        Collection collection = new Collection();
        Drive drive = new Drive(utilities);
        Lift lift = new Lift();

        BeaconDeterminer beaconDeterminer = new BeaconDeterminer();

        waitForStart();

        drive.forward(80);
        drive.turnLeft(90);
        drive.forward(82);
        drive.turnRight(90);
        drive.forward(50);

        if (beaconDeterminer.determine() == BeaconDeterminer.BeaconState.LEFT) {
            drive.turnLeft(90);
            drive.forward(22);
            drive.turnRight(90);
            drive.forward(8);
        } else {
            drive.turnRight(90);
            drive.forward(22);
            drive.turnLeft(90);
            drive.forward(8);
        }

        lift.up();
        collection.release();
        lift.down();

    }
}
