/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.pickup;
import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author liamcook
 */
public class C_PickupBall extends CommandBase {
    
    public C_PickupBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(11);
       //extends arm before picking up
        pickup.rollBallIn();
       while(!pickup.isArmOut())
       {
           pickup.armOut();
       }
       Timer.delay(0.25);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (pickup.hasBall())
        {
            pickup.collectorUp();
            pickup.armIn();
            pickup.rollBallIn((float)0.2);
            setTimeout(0.2);
            Timer.delay(0.21);
        }
        else
        {
            pickup.rollBallIn();
            pickup.collectorDown();
            pickup.armIn();  
        }
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
