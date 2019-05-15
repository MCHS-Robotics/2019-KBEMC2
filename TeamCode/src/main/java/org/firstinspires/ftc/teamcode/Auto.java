package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;
import org.firstinspires.ftc.teamcode.hal.Lift;

@Autonomous(name="Auto", group="KBEMC2")
public class Auto extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        Utilities utilities = new Utilities(this, telemetry, hardwareMap, elapsedTime);


        ComputerVision computerVision = new ComputerVision(utilities);
        Collection collection = new Collection(utilities);
        Drive drive = new Drive(utilities);
        Lift lift = new Lift(utilities);

        BeaconDeterminer beaconDeterminer = new BeaconDeterminer(utilities);

        waitForStart();
        elapsedTime.reset();

        telemetry.addData("Message", "Code has begun executing!");
        telemetry.update();

        drive.forward(77,0.5);
        drive.turnLeft(90);
        drive.forward(50,0.5);
        drive.forward(5,0.3);
        drive.backward(5,0.3);
        drive.turnRight(90);
        drive.forward(50,0.5);

        /*if (beaconDeterminer.determine() == BeaconDeterminer.BeaconState.LEFT) {
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
        collection.release(); commented bc no motor yet
        lift.down();
        */
    }
}
