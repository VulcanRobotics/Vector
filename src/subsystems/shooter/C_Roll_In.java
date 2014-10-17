/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;

/**
 *
 * @author afiol-mahon
 */
public class C_Roll_In extends CommandBase {
    
    public C_Roll_In() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.BallPickup.set(-shooter.BallPickup_Speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.Button_ManualRoller.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.BallPickup.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.BallPickup.set(0);
    }
}
