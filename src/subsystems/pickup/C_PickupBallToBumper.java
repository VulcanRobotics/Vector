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
public class C_PickupBallToBumper extends CommandBase {
    
    public C_PickupBallToBumper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
       //extends arm before picking up
        pickup.rollBallIn(); //can also change speed
       while(!pickup.isArmOut())
       {
           pickup.armOut();
       }
       Timer.delay(0.25);
       setTimeout(0.25); //time ball is rolled up
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
