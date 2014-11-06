/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import robot.OI;

/**
 *
 * @author afiolmahon
 */
public class C_GearShift extends CommandBase {

    int state;

    public C_GearShift(boolean state) {
        requires(shifter);
        this.state = state ? shifter.HIGH: shifter.LOW;
    }
    
    public C_GearShift() {
        requires(shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("C_GearShift started");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shifter.updateShiftThreshold();
        if (OI.Button_ManualExtension.get()) {
            state = shifter.AUTO;
        }
        if (state == shifter.AUTO)
        {
            shifter.autoShift();
        }
        if(state == shifter.HIGH) {
            shifter.setGear(true);
        }
        if (state == shifter.LOW) {
            shifter.setGear(false);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state != shifter.AUTO;
        
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
