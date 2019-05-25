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

        for (int i = 0; i < 1 && opModeIsActive(); i++) {
            // path
            drive.turnRight(180);
            if (left) {
                drive.turnRight(45);
            } else {
                drive.turnLeft(45);
            }
            drive.forward(36, 0.6f);
            if (left) {
                drive.turnLeft(45);
            } else {
                drive.turnRight(45);
            }
            drive.backward(6, 0.6f);
            drive.turnRight(45);

            for (int ii = 0; ii < 4 && opModeIsActive(); ii++) {
                if (bc.collect()) {
                    break;
                }
                drive.forward(58 + (ii >= 2 ? 8 : 0), 0.6f);
                if (ii < 3) {
                    drive.turnLeft(90);
                }
            }

            drive.reverse();
            // lift.up();
            // collection.release();
        }
    }
}
