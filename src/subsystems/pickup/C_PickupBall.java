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
 * @author afiolmahon
 */
public class C_PickupBall extends CommandBase {
    
    public C_PickupBall(){
        requires(pickup);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
       //extends arm before picking up
        pickup.armOut();
       pickup.rollBallIn();
       pickup.collectorDown();
        setTimeout(1); //set amount of time arm should stay out
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (isTimedOut()) {
            pickup.armIn();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pickup.hasBall(); // will hang if it tries to pickup nothing
                                //not really a problem imo because there's no reason to finish auton if
                                //the robot doesn't have a loaded ball
    }

    // Called once after isFinished returns true
    protected void end() {
        pickup.collectorUp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
