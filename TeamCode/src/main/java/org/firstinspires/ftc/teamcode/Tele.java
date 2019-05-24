package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.Lift;

@TeleOp(name="KBEMC2 TeleOp", group="KBEMC2")
public class Tele extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        Utilities utilities = new Utilities(this, telemetry, hardwareMap, elapsedTime);

        ComputerVision computerVision = new ComputerVision(utilities);
        Collection collection = null; // new Collection(utilities);
        Drive drive = new Drive(utilities);
        Lift lift = null; // new Lift(utilities);

        BeaconDeterminer beaconDeterminer = new BeaconDeterminer(utilities);
        BallCollector bc = new BallCollector(utilities, computerVision, drive, collection);

        waitForStart();
        elapsedTime.reset();

        boolean left = false;

        while (opModeIsActive()) {
            // path
            drive.turnRight(180);
            if (left) {
                drive.turnRight(40);
            } else {
                drive.turnLeft(40);
            }
            drive.forward(35, 0.5f);
            if (left) {
                drive.turnLeft(40);
            } else {
                drive.turnRight(40);
            }
            drive.turnRight(45);

            for (int i = 0; i < 4 && opModeIsActive(); i++) {
                if (bc.collect()) {
                    break;
                }
                drive.forward(23, 0.5f);
                if (bc.collect()) {
                    break;
                }
                drive.forward(28, 0.5f);
                drive.turnLeft(90);

            }

            drive.reverse();
            // lift.up();
            // collection.release();
        }
    }
}
