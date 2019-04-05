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

    public Utilities(LinearOpMode opMode, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime elapsedTime) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
        this.elapsedTime = elapsedTime;
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
}