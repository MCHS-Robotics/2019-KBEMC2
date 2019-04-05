package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utilities;

public class Collection {
    private final DcMotor collection;
    public Collection(Utilities utilities) {
        collection = utilities.getHardwareMap().get(DcMotor.class, "collection");
    }

    public void collect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                collection.setPower(1);
                try {
                    wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collection.setPower(0);
            }
        }).start();
    }

    public void release() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                collection.setPower(-1);
                try {
                    wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collection.setPower(0);
            }
        }).start();
    }
}
