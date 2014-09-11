/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import robot.RobotTemplate;

/**
 *
 * @author afiol-mahon
 */
public class ArcadeDrive extends CommandBase {
    
    public ArcadeDrive() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double joy_y = RobotTemplate.oi.getRightStickY();
        double joy_x = RobotTemplate.oi.getRightStickX();
        if(joy_y >= 0.2 || joy_y <= -0.2){
            drive.drive(-joy_y, joy_y);
        }/*else if(joy_x >= 0.2 || joy_x <= -0.2){
            drive.drive(joy_x, joy_x);//One must be inverted for the turn to work
        }*/
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