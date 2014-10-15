/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
/**
 *
 * @author afiolmahon
 */
public class CM_ArcadeDrive extends CommandBase {
    
    public CM_ArcadeDrive() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_ArcadeDrive started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.arcade();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("C_ArcadeDrive ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
