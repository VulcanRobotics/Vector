/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.tension;

import commands.CommandBase;

/**
 *
 * @author afiol-mahon
 */
public class C_Tension_Main extends CommandBase {
    
    public C_Tension_Main() {
        requires(tension);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(tension.isManualMode()){
            tension.tensionPID.disable();
        }else{
            tension.tensionPID.enable();
        }
        tension.tenModule.tensionRangeCheck();
        tension.syncDashboard();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
