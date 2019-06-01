package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="RED: KBEMC2 TeleOp - RIGHT", group="KBEMC2 RED")
public class TeleRedRight extends TeleRed {
    @Override
    protected boolean getBeaconState() {
        return false;
    }
}
