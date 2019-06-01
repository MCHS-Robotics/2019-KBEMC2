package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.hal.Collection;
import org.firstinspires.ftc.teamcode.hal.ComputerVision;
import org.firstinspires.ftc.teamcode.hal.Drive;

public class BallCollector {

    private Utilities utilities;
    private ComputerVision computerVision;
    private Drive drive;
    private Collection collection;

    public BallCollector(Utilities utilities, ComputerVision computerVision, Drive drive, Collection collection) {
        this.utilities = utilities;
        this.computerVision = computerVision;
        this.drive = drive;
        this.collection = collection;
    }

    public boolean collect(boolean canCrashIntoCenterPiece) {
        float angle = computerVision.getHorizontalAngle();
        for (int i = 0; i < 10000 && utilities.getOpMode().opModeIsActive(); i++) {
            utilities.getTelemetry().addData("Theta", angle);
            utilities.getTelemetry().update();
            angle = computerVision.getHorizontalAngle();
        }

        utilities.getTelemetry().addData("Theta", angle);
        utilities.getTelemetry().update();

        if (Float.isNaN(angle)) {
            return false;
        }

        drive.turnRight(angle * 1.23f);

        drive.turnLeft(90);
        drive.forward(6f, 0.5f);
        drive.turnRight(90);

        if (canCrashIntoCenterPiece && ((drive.getRotation() > -45 && drive.getRotation() < 45) || (drive.getRotation() < -135 && drive.getRotation() > -225))) {
            drive.reverse(4);
            return false;
        }
        collection.collect();
        drive.forward(50f, 0.6f);
        return utilities.getOpMode().opModeIsActive();
    }
}
