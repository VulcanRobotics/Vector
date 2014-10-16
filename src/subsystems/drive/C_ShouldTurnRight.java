/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author afiolmahon
 */
public class C_ShouldTurnRight extends CommandBase {
    
    Timer timer = new Timer();
    double delay;
    
    public C_ShouldTurnRight(double delay) {
        requires(drive);
        this.delay = delay;
        timer.start();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(SmartDashboard.getNumber("/SmartDashboard/shouldTurnRight") == 1){
           return true; 
        }else{
            return timer.get() > delay;
        }
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
