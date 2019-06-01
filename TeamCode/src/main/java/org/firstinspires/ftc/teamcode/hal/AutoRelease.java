package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utilities;

public class AutoRelease {
    private Servo release;
    private Utilities utilities;

    public AutoRelease(Utilities utilities) {
        release = utilities.getHardwareMap().get(Servo.class, "release");
        release.setPosition(1);
        this.utilities = utilities;
    }

    public void release() {
        release.setPosition(0);
        utilities.wait_(1000);
    }

    public void reset() {
        release.setPosition(1);
        utilities.wait_(1000);
    }
}
