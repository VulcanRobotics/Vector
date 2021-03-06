/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author afiolmahon
 */
public class C_FindShortTension extends CommandBase {//Used by auton to try to reach tension in under 3 seconds
    
    public C_FindShortTension() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.tensionPID.setPercentTolerance(5);
        shooter.tensionPID.enable();
        shooter.solenoid_trigger.set(false);
        shooter.tensionPID.setSetpoint(DriverStation.getInstance().getAnalogIn(2));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() | shooter.tensionPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.tensionPID.disable();
        System.out.println("FindShortTension complete");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.tensionPID.disable();
    }
}
