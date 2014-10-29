/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.OI;

/**
 *
 * @author afiol-mahon
 */
public class CM_ShooterMain extends CommandBase {
    
    public CM_ShooterMain() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_Shooter_Main Started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        shooter.setExtensions(OI.shouldExtentionsBeOut());
        if (shooter.isShooterDown())
        {
            tension.cock();
            shooter.openLatch();
        }
        else
        {
            shooter.closeLatch();
            tension.setTarget(OI.getShotPower());
        }
        
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("C_Shooter_Main Ended");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
