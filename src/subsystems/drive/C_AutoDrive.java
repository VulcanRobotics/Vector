/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author afiolmahon
 */
public class C_AutoDrive extends CommandBase {
    
    //PID Variables
        double Kp = -0.060;
        double Ki = 0.010;
        double Kd = 0.000;
        double outputRangeHigh = 0.75;
        double outputRangeLow = -0.75;
    //Regulation Values
        double target;
        double threshold = 1.0;
        double gyroScale = 0.01;
    //Control Objects
        PIDController autoDrivePID;
        O_AutoDrivePIDOutput AutoDriveOutput;
    
    public C_AutoDrive(double target, double timeLimit) {
        requires(drive);
        this.target = target;
        AutoDriveOutput = new O_AutoDrivePIDOutput(target);
        autoDrivePID = new PIDController(Kp, Ki, Kd, drive.leftEncoder, AutoDriveOutput);
        autoDrivePID.setOutputRange(outputRangeLow, outputRangeHigh);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        autoDrivePID.setSetpoint(target);
        autoDrivePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(target-drive.leftEncoder.get()) < threshold;
    }

    // Called once after isFinished returns true
    protected void end() {
        autoDrivePID.disable();
        System.out.println("autodrive complete");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        autoDrivePID.disable();
    }
    
    class O_AutoDrivePIDOutput implements PIDOutput{//Defines the component that maps the PIDController to the Motor output
        double PIDControllerValue;
        public O_AutoDrivePIDOutput(double PIDControllerValue){
            this.PIDControllerValue = PIDControllerValue;
        }
        public void pidWrite(double PIDControllerOutput) {//keep passing pid values while above threshold value(pass 0 when below or equal)
            drive.chassis.arcadeDrive(Math.abs(target-drive.leftEncoder.get()) < threshold ? 0 : PIDControllerValue, drive.driveGyro.getAngle()*gyroScale); //drive
        }
    }
}
