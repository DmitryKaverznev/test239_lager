package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class main extends LinearOpMode {
    DcMotor motorLeft = null;
    DcMotor motorRight = null;

    void updateEnc() {
        encLeft = motorLeft.getCurrentPosition();
        encRight = motorRight.getCurrentPosition();
    }

    long encLeft = 0;
    long encRight = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.get(DcMotor.class, "motor1");
        motorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorRight = hardwareMap.get(DcMotor.class, "motor2");
        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        long ONE_STEP = 1440;



        while (opModeIsActive()) {
                updateEnc();
                while (motorLeft.getCurrentPosition() < encLeft + ONE_STEP && motorRight.getCurrentPosition() < encRight + ONE_STEP && opModeIsActive()) {
                    motorLeft.setPower(1);
                    motorRight.setPower(1);
                }
                updateEnc();
                while (motorRight.getCurrentPosition() < encRight + (ONE_STEP) && motorLeft.getCurrentPosition() > encLeft - (ONE_STEP) && opModeIsActive()) {
                    motorLeft.setPower(-1);
                    motorRight.setPower(1);
                }
                updateEnc();
                while (motorLeft.getCurrentPosition() < encLeft + ONE_STEP && motorRight.getCurrentPosition() < encRight + ONE_STEP && opModeIsActive()) {
                    motorLeft.setPower(1);
                    motorRight.setPower(1);
                }
        }
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}
