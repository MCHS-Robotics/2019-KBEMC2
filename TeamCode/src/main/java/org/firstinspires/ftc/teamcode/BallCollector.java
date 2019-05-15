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

    public void collect() {
        float angle = computerVision.getHorizontalAngle();
        while (Float.isNaN(angle) && utilities.getOpMode().opModeIsActive()) {
            utilities.getTelemetry().addData("Theta", angle);
            utilities.getTelemetry().update();
            angle = computerVision.getHorizontalAngle();
        }

        utilities.getTelemetry().addData("Theta", angle);
        utilities.getTelemetry().update();

        drive.turnRight(angle);

        while (computerVision.getBottomYFraction() > 0.4f && utilities.getOpMode().opModeIsActive()) {
            drive.forward(2, 0.5f);
        }

        if (angle < 10) {
            drive.turnLeft(90);
            drive.forward(7f, 0.5f);
            drive.turnRight(90);
        }

        // collection.collect();
        drive.forward(10f, 0.3f);
    }
}
