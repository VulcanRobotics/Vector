/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import subsystems.drive.ArcadeDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class Drive extends Subsystem {
    Victor lDrive = new Victor(RobotMap.PWM_LeftDrive);
    Victor rDrive = new Victor(RobotMap.PWM_RightDrive);
    
            

    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void drive(double leftDrive, double rightDrive){
        lDrive.set(leftDrive);
        rDrive.set(rightDrive);
    }
}
