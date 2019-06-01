package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="BLUE: KBEMC2 TeleOp - RIGHT", group="KBEMC2 BLUE")
public class TeleBlueRight extends TeleBlue {
    @Override
    protected boolean getBeaconState() {
        return false;
    }
}
