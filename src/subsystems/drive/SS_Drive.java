/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
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
        double lowVoltageModifier = 1;
        if(batteryBelow(8.5)){//when voltage drops below 8.5
            lowVoltageModifier = 0.8; // run motors at 80% power
            CommandBase.airSystem.compressor.stop(); //disable compressor
            if(batteryBelow(7.5)){//if below 7.5
                lowVoltageModifier = 0.7; //run motors at 70%
                lockLowGear = true; //force robot to run in low gear
            }
        }else{//when running at high voltages
            CommandBase.airSystem.compressor.start(); //enable compressor
            lockLowGear = false; //allow high gear
        }
        chassis.arcadeDrive(-(CommandBase.oi.driverStick.getY()*lowVoltageModifier), CommandBase.oi.driverStick.getX()*lowVoltageModifier);
        Timer.delay(0.01);
    }
    
    boolean lockLowGear = false; //allows other systems to override gear selection and force robot to use low gear
     public void setGear(boolean state){
        if(!lockLowGear){
            solenoid_gear_shift.set(state);
        }else{
            solenoid_gear_shift.set(true);
        }
     }

    public void syncDashboard() {
        SmartDashboard.putBoolean("Gear Shift", solenoid_gear_shift.get());
    }
    
    double lastVoltage = 12;
    boolean batteryBelow(double testValue){
        double currentVoltage = DriverStation.getInstance().getBatteryVoltage(); //set to current voltage
        double avgVoltage = (currentVoltage+lastVoltage)/2; //averages last and current voltages
        lastVoltage = currentVoltage; //updates last voltage
        return avgVoltage < testValue;
    }
}
