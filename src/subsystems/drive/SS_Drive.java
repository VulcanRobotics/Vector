/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class SS_Drive extends Subsystem {
    RobotDrive chassis = new RobotDrive(RobotMap.PWM_LeftDrive, RobotMap.PWM_RightDrive);
    Solenoid solenoid_gear_shift = new Solenoid(RobotMap.Solenoid_Gear_Shift);


    public void initDefaultCommand() {
        solenoid_gear_shift.set(false); //true is low gear
        setDefaultCommand(new C_ArcadeDrive());
    }
    
    public void arcade(){
        chassis.arcadeDrive(-CommandBase.oi.driverStick.getY(), -CommandBase.oi.driverStick.getX());
        Timer.delay(0.01);
    }
    
    public void setGear(boolean state){
        solenoid_gear_shift.set(state);
    }

    public void syncDashboard() {
        SmartDashboard.putBoolean("Gear Shift", solenoid_gear_shift.get());
    }
}
