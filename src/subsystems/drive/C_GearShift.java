/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;

/**
 *
 * @author afiolmahon
 */
public class C_GearShift extends CommandBase {
    
    boolean finished=false;
    boolean state;
    public C_GearShift(boolean state) {
        requires(drive);
        this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_GearShift started");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.setGear(state);
        finished=true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("C_GearShift ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
