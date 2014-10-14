/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author afiolmahon
 */
public class O_AutoDrivePIDOutput implements PIDOutput{

    double gyroScale = 0.01;
    double threshold = 1.00;
    double target;
    
    public O_AutoDrivePIDOutput(double target){
        this.target = target;
    }

    public void pidWrite(double PIDControllerOutput) {
        double yAxisValue = CommandBase.drive.driveGyro.getAngle()*gyroScale; //y axis is gyro value scaled by our modifier.
        double xAxisValue;
        double testThreshold = target-CommandBase.drive.leftEncoder.get();
        double absTestThreshold = (testThreshold < 0) ? -testThreshold : testThreshold; //this gives the absolute value of our testThreshold
        if(absTestThreshold<threshold){//sets our xAxis value to PIDControllerOutput or 0 depending on the comparison
            xAxisValue = 0;
        }else{
            xAxisValue = PIDControllerOutput;
        }
        CommandBase.drive.chassis.arcadeDrive(yAxisValue, xAxisValue); //drive
    }
    
}
