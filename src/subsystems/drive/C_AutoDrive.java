/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author afiolmahon
 */
public class C_AutoDrive extends CommandBase {
    
    //PID Variables
        double Kp = 1.0;
        double Ki = 0.010;
        double Kd = 10.0;
        double outputRangeHigh = 0.75;
        double outputRangeLow = -0.75;
    //Regulation Values
        double target;
        double threshold = 1.0;
        double gyroScale = 0.01;
    //Control Objects
        PIDController autoDrivePID;
        O_AutoDrivePIDOutput AutoDrivePIDOutput;
    
    public C_AutoDrive(double target) {
        requires(drive);
        this.target = target;
        AutoDrivePIDOutput = new O_AutoDrivePIDOutput();
        autoDrivePID = new PIDController(Kp, Ki, Kd, drive.leftEncoder, AutoDrivePIDOutput);
        autoDrivePID.setOutputRange(outputRangeLow, outputRangeHigh);
        autoDrivePID.setAbsoluteTolerance(0.3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.driveGyro.reset();
        autoDrivePID.setSetpoint(target);
        autoDrivePID.enable();
        System.out.println("AutoDrivePID Enabled: "+autoDrivePID.isEnable());
        System.out.println("AutoDrivePID Setpoint: "+autoDrivePID.getSetpoint());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("AutoDrive Distance Left: "+autoDrivePID.getError());
        System.out.println("AutoDrivePID Enabled: "+autoDrivePID.isEnable());
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return autoDrivePID.onTarget() | !DriverStation.getInstance().isAutonomous();
    }

    // Called once after isFinished returns true
    protected void end() {
        autoDrivePID.disable();
        drive.chassis.arcadeDrive(0, 0);
        System.out.println("AutoDrive Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        autoDrivePID.disable();
        drive.chassis.arcadeDrive(0, 0);
        System.out.println("AutoDrive Interrupted.");
    }
    
    class O_AutoDrivePIDOutput implements PIDOutput{//Defines the component that maps the PIDController to the Motor output
        public void pidWrite(double PIDControllerOutput) {//keep passing pid values while above threshold value(pass 0 when below or equal)
            System.out.println("AutoDrivePIDOutput called with "+PIDControllerOutput+" as motorspeed");
            drive.chassis.arcadeDrive(PIDControllerOutput, drive.driveGyro.getAngle()*gyroScale); //drive
        }
    }
}