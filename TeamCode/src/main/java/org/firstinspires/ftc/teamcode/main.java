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


    private static final double WheelR = 4.8;
    private static final double WheelBetween = 31;
    double getEncWight(int wight) {
        return wight / ((2 * WheelR * 3.14) / (1440));
    }
    double getEncAngel(int angel) {
        return (angel * WheelBetween * 2) / WheelR;
    }


    long encLeft = 0;
    long encRight = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.get(DcMotor.class, "motor1");
        motorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorRight = hardwareMap.get(DcMotor.class, "motor2");
        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();



        while (opModeIsActive()) {
            updateEnc();
            while (motorLeft.getCurrentPosition() < encLeft + getEncWight(30) && motorRight.getCurrentPosition() < encRight + getEncWight(30) && opModeIsActive()) {
                motorLeft.setPower(1);
                motorRight.setPower(1);
            }

            motorLeft.setPower(0);
            motorRight.setPower(0);
            sleep(1000);

            updateEnc();
            while (motorRight.getCurrentPosition() < encRight + getEncAngel(90) && motorLeft.getCurrentPosition() > encLeft - getEncAngel(90) && opModeIsActive()) {
                motorLeft.setPower(-1);
                motorRight.setPower(1);
            }


        }
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}
