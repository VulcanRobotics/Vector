/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import Auton.CG_Auton0;
import Auton.CG_Auton1;
import Auton.CG_Auton2;
import Auton.CG_Auton3;
import Auton.CG_Auton4;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.drive.C_CalibrateEncoderGyro;

/**
 *
 * @author afiol-mahon
 */
public class AutonCommand extends CommandBase {
    
    public AutonCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.airSystem.compressor.start();
        Scheduler.getInstance().add(new C_CalibrateEncoderGyro());
        int autonMode = (int)DriverStation.getInstance().getAnalogIn(1);
        if(autonMode == 0){
            Scheduler.getInstance().add(new CG_Auton0());
        }else if(autonMode == 1){
            Scheduler.getInstance().add(new CG_Auton1());
        }else if(autonMode == 2){
            Scheduler.getInstance().add(new CG_Auton2());
        }else if(autonMode == 3){
            Scheduler.getInstance().add(new CG_Auton3());
        }else if(autonMode == 4){
            Scheduler.getInstance().add(new CG_Auton4());
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Tension Potentiometer", shooter.tenModule.tenPot.pidGet()); //Report Tension
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
