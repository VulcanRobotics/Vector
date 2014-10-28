/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.pickup;

import commands.CommandBase;
import robot.OI;
/**
 *
 * @author liamcook
 */
public class A_PickupBall extends CommandBase {
    
    public A_PickupBall() {
        // Use requires() here to declare subsystem dependencies
        requires(pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pickup.armOut();
        if (pickup.hasBall())
        {
            pickup.collectorUp();
            pickup.stopRollers();
        }
        else
        {
            pickup.collectorDown();
            pickup.rollBallIn();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("executing A_Pickupball ... bad .. this is an error");
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
