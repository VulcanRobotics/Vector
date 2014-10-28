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
public class C_Eject_Ball extends CommandBase {
    
    double timeout = 0;
    
    public C_Eject_Ball() {
        requires(pickup);
    }
    public C_Eject_Ball(double timeout) {
        requires(pickup);
        this.timeout = timeout;
        setTimeout(this.timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pickup.rollBallOut();
        pickup.collectorDown();
        pickup.armIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("executing A_Eject_Ball");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
