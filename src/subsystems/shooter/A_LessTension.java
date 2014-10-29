/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;

/**
 *
 * @author 1218
 */
public class A_LessTension extends CommandBase {
    
    public A_LessTension() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       requires(tension);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        tension.disable();
        System.out.println("moving to less tension");
        tension.moveUp(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        tension.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
