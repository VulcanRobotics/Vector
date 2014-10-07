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
public class C_Tension_ManualRaise extends CommandBase {
    double speed;
    boolean finished = false;
    
    public C_Tension_ManualRaise(double speed) {
        requires(shooter);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_Tension_ManualRaise started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.tenModule.setManualTension(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !CommandBase.oi.Button_ManualRaiseTension.get();

    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.tenModule.setTension(0);
        System.out.println("C_Tension_ManualRaise ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
