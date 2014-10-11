/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;

/**
 *
 * @author afiolmahon
 */
public class C_ForceCollectorDown extends CommandBase {
    
    public C_ForceCollectorDown() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.solenoid_collector.set(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.Button_ForceCollectorDown.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.collectorRoutine();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.collectorRoutine();
    }
}
