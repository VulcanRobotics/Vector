/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
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
    //Gyro
        Gyro driveGyro = new Gyro(RobotMap.AI_Gyro);
        O_DrivePIDOutput PIDDrive = new O_DrivePIDOutput();
    //Autodrive
        Encoder leftEncoder = new Encoder(RobotMap.DIO1_LeftEncoder, RobotMap.DIO2_LeftEncoder);
    
    public void initDefaultCommand() {
        solenoid_gear_shift.set(false); //Init Solenoid(true is low gear).
        setDefaultCommand(new CM_ArcadeDrive());
    }
    
    public void arcade(){//Joystick drive method, additionally checks voltage for robot, and if battery is dying, compensates to extend battery life.
        double lowVoltageModifier = 1;
        if(batteryBelow(8.5)){//Stops compressor and runs motors at 80% if battery drops below 8.5 volts.
            lowVoltageModifier = 0.8;
            CommandBase.airSystem.compressor.stop();
            if(batteryBelow(7.5)){//Forces robot to use low gear and runs motors at 75% if battery drops below 7.5 volts.
                lowVoltageModifier = 0.7;
                lockLowGear = true;
            }
        }else{//Ensures we can shift gears and compressor is running at high voltages
            CommandBase.airSystem.compressor.start();
            lockLowGear = false;
        }
        chassis.arcadeDrive(-(CommandBase.oi.driverStick.getY()*lowVoltageModifier), -(CommandBase.oi.driverStick.getX()*lowVoltageModifier));
        Timer.delay(0.01);
    }
    
    boolean lockLowGear = false; //Allows other systems to override gear selection and force robot to use low gear.
     public void setGear(boolean state){
        if(!lockLowGear){
            solenoid_gear_shift.set(state);
        }else{
            solenoid_gear_shift.set(true);
        }
     }

    public void syncDashboard() {
        SmartDashboard.putBoolean("Gear Shift", solenoid_gear_shift.get());
        SmartDashboard.putNumber("Gyro Angle", driveGyro.getAngle());
    }
    
    double lastVoltage = 12;
    boolean batteryBelow(double testValue){//Method returns true if robot voltage is below the testValue
        double currentVoltage = DriverStation.getInstance().getBatteryVoltage(); //set to current voltage
        double avgVoltage = (currentVoltage+lastVoltage)/2; //averages last and current voltages
        lastVoltage = currentVoltage; //updates last voltage
        return avgVoltage < testValue;
    }
}
