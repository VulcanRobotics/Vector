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
    
    double timeout = 11.0;
    boolean finished = false;
    
    public C_PickupBall() {
        requires(pickup);
    }
    public C_PickupBall(double timeout){
        requires(pickup);
        this.timeout = timeout;
        setTimeout(this.timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       //extends arm before picking up
        pickup.rollBallIn();
        pickup.armOut();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (pickup.hasBall()){
            pickup.collectorUp();
            pickup.armIn();
            pickup.rollBallIn(0.2f);
            Timer.delay(0.25);
            finished = true;
        }
        else{
            pickup.rollBallIn();
            pickup.collectorDown();
            pickup.armOut();  
        }
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
