/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.pickup;

import commands.CommandBase;

/**
 *
 * @author liamcook
 */
public class A_MoveArmOut extends CommandBase {
    
    public A_MoveArmOut() {
        requires(pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pickup.armOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
