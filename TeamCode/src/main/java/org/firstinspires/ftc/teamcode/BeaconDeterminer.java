package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BeaconDeterminer {
    private ColorSensor color;
    private Utilities.Side side;
    private Telemetry telemetry;

    public BeaconDeterminer(Utilities utilities) {
        this.color = utilities.getHardwareMap().get(ColorSensor.class, "color");
        this.side = utilities.getSide();
        this.telemetry = utilities.getTelemetry();
    }

    public BeaconState determine() {
        telemetry.addData("Red", color.red());
        telemetry.addData("Blue", color.blue());
        telemetry.update();
        if (Math.abs(color.red() - color.blue()) > 20f) {
            if (color.blue() > color.red()) {
                if (side == Utilities.Side.RED) {
                    return BeaconState.RIGHT;
                } else {
                    return BeaconState.LEFT;
                }
            } else {
                if (side == Utilities.Side.RED) {
                    return BeaconState.LEFT;
                } else {
                    return BeaconState.RIGHT;
                }
            }
        } else {
            return BeaconState.CONFUSED;
        }
    }

    public enum BeaconState {
        LEFT, RIGHT, CONFUSED
    }
}
