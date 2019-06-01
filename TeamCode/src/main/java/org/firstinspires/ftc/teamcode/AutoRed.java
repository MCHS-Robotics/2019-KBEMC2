package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="RED: KBEMC2 Auto", group="KBEMC2")
public class AutoRed extends Auto {
    @Override
    protected Utilities.Side getSide() {
        return Utilities.Side.RED;
    }
}
