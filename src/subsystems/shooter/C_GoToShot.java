/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;

/**
 *command that cock and finds desired tension
 * combines SS_Tension and SS_Shooter
 * 
 * 
 * @author liamcook
 */
public class C_GoToShot extends CommandBase {
    
    double target;
    
    public C_GoToShot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
        requires(tension);
    }

    public C_GoToShot(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
        requires(tension);
        this.target = target;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.extensionsOut();
        if (shooter.isShooterDown())
        {
            tension.cock();
            shooter.openLatch();
        }
        else
        {
            shooter.closeLatch();
            tension.setTarget(target);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooter.isShooterDown() && tension.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        tension.cock();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}