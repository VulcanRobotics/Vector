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
public class C_ShooterFire extends CommandBase {
    
    public C_ShooterFire() {
        requires(shooter);
    }
    
    boolean finished = false;

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_ShooterFire started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(shooter.shooterDown.get()){
            arm.extend();
            if(!arm.Arm_Out.get() | shooter.tenModule.tenPot.pidGet() < 1.5){//can fire if arm is our or tension is below 1.5
                            shooter.solenoid_trigger.set(true);
                            finished = true;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.Button_Trigger.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("C_ShooterFire ended");
        arm.retract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("C_ShooterFire interrupted");
        arm.retract();
    }
}
