package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="BLUE: KBEMC2 TeleOp - LEFT", group="KBEMC2 BLUE")
public class TeleBlueLeft extends TeleBlue {
    @Override
    protected boolean getBeaconState() {
        return true;
    }
}
