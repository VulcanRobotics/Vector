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
public class C_Tension_ManualRaise extends CommandBase {
    double speed;
    boolean finished = false;
    
    public C_Tension_ManualRaise(double speed) {
        requires(tension);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        tension.tenModule.setManualTension(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !CommandBase.oi.Button_ManualRaiseTension.get();

    }

    // Called once after isFinished returns true
    protected void end() {
        tension.tenModule.setTension(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
