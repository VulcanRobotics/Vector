/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class SS_Drive extends Subsystem {
    RobotDrive chassis = new RobotDrive(RobotMap.PWM_LeftDrive, RobotMap.PWM_RightDrive);

    public void initDefaultCommand() {
        setDefaultCommand(new C_ArcadeDrive());
    }
    
    public void arcade(){
        chassis.arcadeDrive(CommandBase.oi.leftStick);
        Timer.delay(0.01);
    }
}
