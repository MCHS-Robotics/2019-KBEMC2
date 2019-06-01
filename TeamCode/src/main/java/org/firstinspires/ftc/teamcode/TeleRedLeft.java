package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="RED: KBEMC2 TeleOp - LEFT", group="KBEMC2 RED")
public class TeleRedLeft extends TeleRed {
    @Override
    protected boolean getBeaconState() {
        return true;
    }
}
