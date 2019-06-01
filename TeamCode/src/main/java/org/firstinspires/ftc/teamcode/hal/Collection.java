package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utilities;

public class Collection {
    private final DcMotor collection;
    private final Utilities utilities;

    public Collection(Utilities utilities) {
        collection = utilities.getHardwareMap().get(DcMotor.class, "collection");
        collection.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.utilities = utilities;
    }

    public void collect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ElapsedTime elapsedTime = new ElapsedTime();
                elapsedTime.reset();

                collection.setPower(1.0f);
                while (elapsedTime.seconds() < 8 && utilities.getOpMode().opModeIsActive());
                collection.setPower(0);

            }
        }).start();
    }

    public void release() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ElapsedTime elapsedTime = new ElapsedTime();
                elapsedTime.reset();

                collection.setPower(-0.5f);
                while (elapsedTime.seconds() < 2 && utilities.getOpMode().opModeIsActive());
                collection.setPower(0);

            }
        }).start();
    }
}
