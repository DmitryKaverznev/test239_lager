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
            if (gamepad1.dpad_up) {
                motorRight.setPower(1);
                motorLeft.setPower(1);
            } else if (gamepad1.dpad_down) {
                motorRight.setPower(-1);
                motorLeft.setPower(-1);
            } else if (gamepad1.dpad_left) {
                motorLeft.setPower(1);
                motorRight.setPower(-1);
            } else if (gamepad1.dpad_right) {
                motorRight.setPower(1);
                motorLeft.setPower(-1);
            } else if (Math.abs(gamepad1.left_stick_y)>0.1) {
                double power = Math.abs(gamepad1.left_stick_y) * gamepad1.left_stick_y * -1;

                motorRight.setPower(power);
                motorLeft.setPower(power);
            }
            else if (Math.abs(gamepad1.right_stick_x)>0.1) {
                double power = Math.abs(gamepad1.right_stick_x) * gamepad1.right_stick_x;

                motorRight.setPower(power);
                motorLeft.setPower(-power);
            }
            else {
                motorLeft.setPower(0);
                motorRight.setPower(0);
            }
        }

    }
}
