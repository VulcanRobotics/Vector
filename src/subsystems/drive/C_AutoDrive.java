/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;

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

        double target;
        
        PIDController autoDrivePID;
        O_AutoDrivePIDOutput AutoDriveOutput;

    public C_AutoDrive(double target) {
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
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        autoDrivePID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        autoDrivePID.disable();
    }
}
