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
public class C_PickupBallToBumper extends CommandBase {
    
    boolean finished = false;
    
    public C_PickupBallToBumper() {
        requires(pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       //extends arm before picking up
       pickup.armOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(pickup.isArmOut()){
            pickup.rollBallIn(); //can also change speed
            Timer.delay(0.25);
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
