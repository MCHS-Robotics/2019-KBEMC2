package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="RED: KBEMC2 TeleOp", group="KBEMC2 RED")
public abstract class TeleRed extends Tele {
    @Override
    protected Utilities.Side getSide() {
        return Utilities.Side.RED;
    }
}
