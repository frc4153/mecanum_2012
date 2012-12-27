/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.team4153.mercanum;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Mercanum extends SimpleRobot {

    public static final int LEFT_FRONT_MOTOR = 4;
    public static final int LEFT_REAR_MOTOR = 2;
    public static final int RIGHT_FRONT_MOTOR = 8;
    public static final int RIGHT_REAR_MOTOR = 6;
    final int GYRO_CHANNEL = 1;
    Joystick stick1 = new Joystick(1); //There is an option for Joystick 
    //which takes 3 ints. The other two ints are very important. I don't
    //know what to do with them.
    Joystick stick2 = new Joystick(2); //This joystick is in port 2
    Joystick stick3 = new Joystick(3); //Are the ports 0-indexed? Beats me.
    Gyro gyro = new Gyro(GYRO_CHANNEL); //It's a Gyro, called gypo
    RobotDrive drive;
    /**
     * Right front CAN motor.
     */
    CANJaguar rightFront;
    /**
     * Right rear CAN motor.
     */
    CANJaguar rightRear;
    /**
     * Left front CAN motor.
     */
    CANJaguar leftFront;
    /**
     * Left rear CAN motor.
     */
    CANJaguar leftRear;

    /**
     *
     */
    protected void robotInit() {
        // don't need to call the superclass super.robotInit();
        try {
            rightFront = new CANJaguar(RIGHT_FRONT_MOTOR);
            rightRear = new CANJaguar(RIGHT_REAR_MOTOR);
            leftFront = new CANJaguar(LEFT_FRONT_MOTOR);
            leftRear = new CANJaguar(LEFT_REAR_MOTOR);

            drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);

            drive.setSafetyEnabled(false);
        } catch (Exception any) {
            any.printStackTrace();
        }
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            System.out.println("X: " + stick1.getX() + " Y: " + stick1.getY() + " Twist: " + stick1.getTwist() + " Angle: " +  gyro.getAngle() );
            drive.mecanumDrive_Cartesian(stick1.getX()/4, stick1.getY()/4, stick1.getTwist(), gyro.getAngle());
        }
    }
}
