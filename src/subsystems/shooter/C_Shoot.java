/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;

/**
 *
 * @author liamcook
 */
public class C_Shoot extends CommandBase {
    
    double timeout = 5;
    
    public C_Shoot() {
        requires(shooter);
    }
    public C_Shoot(double timeout){
        requires(shooter);
        this.timeout = timeout;
        setTimeout(this.timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.openLatch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (isTimedOut())
        {
            System.out.println("trying to shoot for ["+timeout+"] seconds - cannot");
            return true;
        }
        return !shooter.isShooterDown();
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
