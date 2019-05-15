package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;

public class BeaconDeterminer {
    private ColorSensor color;
    private Utilities.Side side;

    public BeaconDeterminer(Utilities utilities) {
        this.color = utilities.getHardwareMap().get(ColorSensor.class, "color");
        this.side = utilities.getSide();
    }

    public BeaconState determine() {
        if (Math.abs(color.red() - color.blue()) > 20f) {
            if (color.blue() > color.red()) {
                if (side == Utilities.Side.BLUE) {
                    return BeaconState.RIGHT;
                } else {
                    return BeaconState.LEFT;
                }
            } else {
                if (side == Utilities.Side.BLUE) {
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
