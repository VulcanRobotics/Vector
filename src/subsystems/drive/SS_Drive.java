/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.CounterBase;
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
    //Outputs
        public RobotDrive chassis = new RobotDrive(RobotMap.PWM_LeftDrive, RobotMap.PWM_RightDrive);
        Solenoid solenoid_gear_shift = new Solenoid(RobotMap.Solenoid_Gear_Shift);
    //Sensors
        public Gyro driveGyro = new Gyro(RobotMap.AI_Gyro){{
            this.setSensitivity(-0.007);
            this.reset();
        }};
        public Encoder leftEncoder = new Encoder(RobotMap.DIO1_LeftEncoder, RobotMap.DIO2_LeftEncoder, true, CounterBase.EncodingType.k2X){{
            this.setPIDSourceParameter(PIDSourceParameter.kDistance);
            this.setDistancePerPulse(((4.0*3.14159*(40.0/45.0))/100.0/12.0));
            this.start();            
        }};
            
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
        
        double trim = 0.8;
        if(CommandBase.oi.Button_JoystickMode.get()){
            chassis.arcadeDrive(-(CommandBase.oi.driverStick.getY()*(lowVoltageModifier == 1 ? 1 : lowVoltageModifier)), -(CommandBase.oi.driverStick.getX()*(lowVoltageModifier == 1 ? trim : lowVoltageModifier)));
        }else{
            chassis.arcadeDrive(-CommandBase.oi.driverStick.getRawAxis(2), -CommandBase.oi.driverStick.getRawAxis(4));
        }
        Timer.delay(0.01);
    }
    
    boolean lockLowGear = false; //Allows other systems to override gear selection and force robot to use low gear.
     public void setGear(boolean state){
        solenoid_gear_shift.set(lockLowGear ? true : state);
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
    
    public void stopDrive(){
        chassis.drive(0, 0);
    }
}