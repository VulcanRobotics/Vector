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
public class A_ShooterFire extends CommandBase {
    
    boolean finished = false;
    public A_ShooterFire() {
        requires(shooter);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_ShooterFire started");
<<<<<<< HEAD
        shooter.openLatch(); 
=======
        if(shooter.shooterDown.get()){
//extend arm
            System.out.println("Waiting for shooterDown Switch");
            if(pickup.isArmOut() | shooter.tenModule.tenPot.pidGet() < 1.5){//can fire if arm is out or tension is below 1.5
                System.out.println("Shoot Conditions met");
                shooter.solenoid_trigger.set(true);
            }
        }
        if(!oi.Button_Trigger.get()){
            finished = true;
        }
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
<<<<<<< HEAD
        return false;
=======
        return finished;
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("C_ShooterFire ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("C_ShooterFire interrupted");
       
    }
}
