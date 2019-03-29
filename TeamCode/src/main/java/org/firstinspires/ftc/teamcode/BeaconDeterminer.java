package org.firstinspires.ftc.teamcode;

public class BeaconDeterminer {
    public BeaconState determine() {
        return BeaconState.LEFT;
    }

    public enum BeaconState {
        LEFT, RIGHT
    }
}
