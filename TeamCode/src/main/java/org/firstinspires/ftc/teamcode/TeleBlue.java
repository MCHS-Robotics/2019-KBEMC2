package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public abstract class TeleBlue extends Tele {
    @Override
    protected Utilities.Side getSide() {
        return Utilities.Side.BLUE;
    }
}
