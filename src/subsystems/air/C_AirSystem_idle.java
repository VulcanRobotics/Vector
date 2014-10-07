/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.air;

import commands.CommandBase;

/**
 *
 * @author afiol-mahon
 */
public class C_AirSystem_idle extends CommandBase {
    
    public C_AirSystem_idle() {
        requires(airSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_AirSystem_idle started");
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
        System.out.println("C_AirSystem_idle ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
