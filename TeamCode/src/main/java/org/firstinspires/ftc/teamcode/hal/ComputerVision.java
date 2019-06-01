package org.firstinspires.ftc.teamcode.hal;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Utilities;

import java.util.List;

public class ComputerVision {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = " ATiuXRv/////AAABmX/tug8P6EteuRJz2PTAi6JMBeLa9Te+gCRaTBPeDZ77UloArIT7REsZPIosl4YG0JLDyl4+yj3lpzfzKIkpOQNRfsgfAjS6tTbwBRHJsnStRDKMwb4Fj5l3rTCxB8qHn8GW45O1BGLuAROQ+DrNs26ktJV3HTEr6N4XYXSdDD3UX+2Yj8u4CmJ6xk4kY0JdX/Kklw4Ai0Mba5vFviXXjue5UMQRZTIy45y2h8UpEcSFeqiLLKdGktA5qL5NufN0/KZXI3EQNHjmrAi52oqWiO7JBAolc9uC7B910YGiGI6E0a/KJAvxLY6zlKuXI+XkQP9WgGwfXUZhU8nTyKnEDi4HY0v/+uSmKfcyIrDWf2KW";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private boolean first = true;

    public ComputerVision(Utilities utilities) {
        initVuforia(utilities.getHardwareMap());
        initTfod(utilities.getHardwareMap());
    }

    public float getHorizontalAngle() {
        if (first) { first = false; tfod.activate(); }

        List<Recognition> recognitions = tfod.getRecognitions();

        if (recognitions == null || recognitions.isEmpty()) { return Float.NaN; }

        Recognition closest = null;
        for (Recognition r : recognitions) {
            if (closest == null) { closest = r; }

            if (r.estimateAngleToObject(AngleUnit.DEGREES) > closest.estimateAngleToObject(AngleUnit.DEGREES)) {
                closest = r;
            }
        }

        return (float) closest.estimateAngleToObject(AngleUnit.DEGREES);
    }

    public float getBottomYFraction() {
        if (first) { first = false; tfod.activate(); }

        List<Recognition> recognitions = tfod.getRecognitions();

        if (recognitions == null || recognitions.isEmpty()) { return Float.NaN; }

        Recognition closest = null;
        for (Recognition r : recognitions) {
            if (closest == null) { closest = r; }

            if (r.getBottom() < closest.getBottom()) {
                closest = r;
            }
        }

        return closest.getBottom() / closest.getImageHeight();
    }

    private void initVuforia(HardwareMap hardwareMap) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "camera");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod(HardwareMap hardwareMap) {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.6f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
