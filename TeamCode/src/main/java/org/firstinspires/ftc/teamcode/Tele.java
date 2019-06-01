package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.Lift;

public abstract class Tele extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    protected abstract Utilities.Side getSide();
    protected abstract boolean getBeaconState();

    @Override
    public void runOpMode() {

        Utilities utilities = new Utilities(this, telemetry, hardwareMap, getSide(), elapsedTime);

        ComputerVision computerVision = new ComputerVision(utilities);
        Collection collection = new Collection(utilities);
        Drive drive = new Drive(utilities);
        Lift lift = new Lift(utilities);

        BallCollector bc = new BallCollector(utilities, computerVision, drive, collection);

        waitForStart();
        elapsedTime.reset();

        boolean left = getBeaconState();

        for (;opModeIsActive();) {
            // path
            drive.forward(2, 0.1f);
            drive.reset();

            drive.backward(14, 0.6f);
            lift.down();
            drive.turnRight(90);
            if (left) {
                drive.forward(3, 0.6f, true);
            } else {
                drive.backward(3, 0.6f, true);
            }
            drive.turnRight(90);
            if (left) {
                drive.turnRight(45);
            } else {
                drive.turnLeft(45);
            }
            drive.forward(32, 0.6f);
            if (left) {
                drive.turnLeft(45);
            } else {
                drive.turnRight(45);
            }
            drive.backward(6, 0.6f);
            drive.turnRight(45);

            for (int ii = 0; ii < 4 && opModeIsActive(); ii++) {
                if (bc.collect(ii % 2 == 0)) {
                    break;
                }
                drive.forward(58 + (ii >= 2 ? 8 : 0), 0.6f);
                if (bc.collect(false)) {
                    break;
                }
                if (ii < 3) {
                    drive.turnLeft(90);
                }
            }

            lift.up();
            drive.reverse();
            collection.release();
            utilities.wait_(1000);
        }
    }
}
