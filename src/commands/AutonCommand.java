/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author afiol-mahon
 */
public class AutonCommand extends CommandBase {
    
    boolean finished = false;
    
    public AutonCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.airSystem.compressor.start();
        finished = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("AutonCommand Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("AutonCommand Interrupted");
    }
}
