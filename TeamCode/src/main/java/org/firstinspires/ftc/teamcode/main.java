package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class main extends LinearOpMode {
    DcMotor motorA = null;
    DcMotor motorB = null;
    DcMotor motorC = null;
    DcMotor motorD = null;

    void updateEnc() {
        encA = motorA.getCurrentPosition();
        encB = motorB.getCurrentPosition();
        encC = motorC.getCurrentPosition();
        encD = motorD.getCurrentPosition();
    }


    private static final double WheelR = 4.8;
    private static final double WheelBetween = 31;

    double getEncWight(int wight) {
        return wight / ((2 * WheelR * 3.14) / (1440));
    }

    double getEncAngel(int angel) {
        return (angel * WheelBetween * 2) / WheelR;
    }

    void move(int up, int right, int power) {
        if (up == 1 && right == 0) {
            motorB.setPower(power);
            motorC.setPower(power);
            motorD.setPower(power);
            motorA.setPower(power);
        } else if (up == 1 && right == 1) {
            motorA.setPower(power);
            motorB.setPower(0);
            motorC.setPower(0);
            motorD.setPower(power);
        } else if (up == 0 && right == 1) {
            motorA.setPower(power);
            motorB.setPower(-power);
            motorC.setPower(-power);
            motorD.setPower(power);
        } else if (up == -1 && right == 1) {
            motorA.setPower(-power);
            motorB.setPower(0);
            motorC.setPower(0);
            motorD.setPower(-power);
        } else if (up == -1 && right == 0) {
            motorA.setPower(-power);
            motorB.setPower(-power);
            motorD.setPower(-power);
            motorC.setPower(-power);
        } else if (up == -1 && right == -1) {
            motorA.setPower(0);
            motorB.setPower(-1);
            motorC.setPower(-1);
            motorD.setPower(0);
        } else if (up == 0 && right == -1) {
            motorA.setPower(-1);
            motorB.setPower(1);
            motorC.setPower(1);
            motorD.setPower(-1);
        } else if (up == 1 && right == -1) {
            motorA.setPower(0);
            motorB.setPower(1);
            motorC.setPower(1);
            motorD.setPower(0);
        } else if (up == 0 && right == 0) {
            motorA.setPower(0);
            motorB.setPower(0);
            motorC.setPower(0);
            motorD.setPower(0);
        }
    }

    long encA = 0;
    long encB = 0;
    long encC = 0;
    long encD = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        motorA = hardwareMap.get(DcMotor.class, "motor0");
        motorA.setDirection(DcMotorSimple.Direction.FORWARD);
        motorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorB = hardwareMap.get(DcMotor.class, "motor1");
        motorB.setDirection(DcMotorSimple.Direction.FORWARD);
        motorB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorC = hardwareMap.get(DcMotor.class, "motor2");
        motorC.setDirection(DcMotorSimple.Direction.REVERSE);
        motorC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorC.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorD = hardwareMap.get(DcMotor.class, "motor3");
        motorD.setDirection(DcMotorSimple.Direction.FORWARD);
        motorD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                move(1, 1, 1);
            } else if (gamepad1.dpad_down) {
                move(1, -1, 1);
            } else if (gamepad1.dpad_left) {
                move(-1, 1,1);
            } else if (gamepad1.dpad_right) {
                move(-1, -1, 1);
            } else {
                move(0, 0, 0);
            }
        }
    }
}