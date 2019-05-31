package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.teamcode.Utilities;

public class IMU {
    private ElapsedTime elapsedTime;
    private BNO055IMU imu;
    private Utilities utilities;

    private float rotation;

    public IMU(Utilities utilities) {
        initIMU(utilities.getHardwareMap());
        elapsedTime = new ElapsedTime();
        rotation = 0;
        this.utilities = utilities;
    }

    public void update() {
        AngularVelocity L = imu.getAngularVelocity();
        rotation -= elapsedTime.seconds() * L.xRotationRate;

        utilities.getTelemetry().addData("Omega", L.xRotationRate);

        elapsedTime.reset();
    }

    public void resetElapsedTime() {
        elapsedTime.reset();
        rotation = 0;
    }

    public float getRotation() {
        return rotation;
    }

    private void initIMU(HardwareMap hardwareMap) {
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        params.calibrationDataFile = "BNO055IMUCalibration.json";
        params.loggingEnabled = true;
        params.loggingTag = "IMU";
        params.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(params);
    }
}
