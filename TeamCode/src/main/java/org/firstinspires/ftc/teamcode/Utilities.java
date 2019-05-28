package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Utilities {
    private LinearOpMode opMode;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private ElapsedTime elapsedTime;
    private Side side;

    public Utilities(LinearOpMode opMode, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime elapsedTime) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
        this.elapsedTime = elapsedTime;
        this.side = Side.BLUE;
    }

    public LinearOpMode getOpMode() {
        return opMode;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    public ElapsedTime getElapsedTime() {
        return elapsedTime;
    }

    public void wait_(float millis) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.milliseconds() < millis && opMode.opModeIsActive());
    }

    public Side getSide() { return side; }

    public enum Side {
        RED, BLUE
    }
}
