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
        if(oi.Button_Reload.get() && !shooter.shooterDown.get()){//If we are in reload mode, and shooter is not down, open trigger and lower shooter.
            shooter.tensionPID.setSetpoint(shooter.uncockTension);
            shooter.solenoid_trigger.set(true);
        }else{//Bring tension to setpoint and ensure trigger is closed
            shooter.solenoid_trigger.set(false);
            shooter.tensionPID.setSetpoint(shooter.configureShot());
        }
        shooter.manualCheck();//enables or disables PID based on manual switch
        shooter.tenModule.tensionRangeCheck();//Precautional range check to prevent limits from being exceeded.
        shooter.collectorRoutine();//Sets collector up when a ball is detected
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
