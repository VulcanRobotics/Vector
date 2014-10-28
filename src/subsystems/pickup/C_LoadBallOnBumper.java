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
 * @author afiolmahon
 */
public class C_LoadBallOnBumper extends CommandBase {
    
    boolean finished = false;
    
    public C_LoadBallOnBumper() {
        requires(pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pickup.rollBallIn(0.5f);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(pickup.hasBall()){
            finished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        pickup.stopRollers();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        pickup.stopRollers();
    }
}
