/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.arm;

import commands.CommandBase;

/**
 *
 * @author afiol-mahon
 */
public class C_Extend extends CommandBase {
    
    public C_Extend() {
        requires(arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        arm.extend();
        arm.BallPickup.set(-arm.BallPickup_Speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.Button_Pickup.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        arm.retract();
        arm.BallPickup.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
