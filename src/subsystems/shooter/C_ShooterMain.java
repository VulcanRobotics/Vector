/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;

/**
 *
 * @author afiol-mahon
 */
public class C_ShooterMain extends CommandBase {
    
    public C_ShooterMain() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_Shooter_Main Started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.manualCheck();//enables or disables PID based on manual switch
        shooter.tensionPID.setSetpoint(shooter.tenModule.getTensionTargetSelect());
        shooter.tenModule.tensionRangeCheck();
        shooter.syncDashboard();
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
