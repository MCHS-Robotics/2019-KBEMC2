package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Utilities {
    private LinearOpMode opMode;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Utilities(LinearOpMode opMode, Telemetry telemetry, HardwareMap hardwareMap) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
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
}
