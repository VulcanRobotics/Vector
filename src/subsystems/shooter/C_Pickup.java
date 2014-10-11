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
public class C_Pickup extends CommandBase {
    
    public C_Pickup() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {//Extends arm and starts pickup roller.
        shooter.extendArm();
        shooter.BallPickup.set(-shooter.BallPickup_Speed);
        shooter.solenoid_collector.set(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.Button_Pickup.get();
    }

    // Called once after isFinished returns true
    protected void end() {//Stops roller and retracts arm when complete.
        shooter.retractArm();
        shooter.BallPickup.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.retractArm();
        shooter.BallPickup.set(0);
    }
}
