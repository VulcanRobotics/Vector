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
public class C_GyroSpin extends CommandBase {
    
    //PID
    double OutputRangeTop = 0.7;
    double OutputRangeBottom = -0.7;
    double Kp = 3.000;
    double Ki = 0.000;
    double Kd = 0.000;
    PIDController GyroPid = new PIDController(Kp, Ki, Kd, drive.driveGyro, drive.PIDDrive);
    double desiredAngle;
    
    public C_GyroSpin(double desiredAngle) {
        requires(drive);
        this.desiredAngle = desiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        GyroPid.setSetpoint(Kp);
        GyroPid.setOutputRange(OutputRangeBottom, OutputRangeTop);
        GyroPid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.Button_AutoGyroDrive.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        GyroPid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        GyroPid.disable();
    }
}
