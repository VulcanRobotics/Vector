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
        requires(pickup);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_ShooterFire started");
        pickup.armOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        pickup.armOut();
        finished = shooter.openLatch();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        pickup.setDefaultState();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
       pickup.setDefaultState();
    }
}
