package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="BLUE: KBEMC2 Auto", group="KBEMC2")
public class AutoBlue extends Auto {
    @Override
    protected Utilities.Side getSide() {
        return Utilities.Side.BLUE;
    }
}
