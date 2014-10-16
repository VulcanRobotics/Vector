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
    
    Timer timer = new Timer();
    public C_FindShortTension() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.tensionPID.enable();
        shooter.solenoid_trigger.set(false);
        shooter.tensionPID.setSetpoint(DriverStation.getInstance().getAnalogIn(2));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 3 | shooter.tensionPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.tensionPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.tensionPID.disable();
    }
}
