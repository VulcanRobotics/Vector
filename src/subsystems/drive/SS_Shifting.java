/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.OI;
import robot.RobotMap;
/**
 *
 * @author lcook
 */
public class SS_Shifting extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid solenoid_gear_shift = new Solenoid(RobotMap.Solenoid_Gear_Shift);
    
    public Encoder leftEncoder = new Encoder(RobotMap.DIO1_LeftEncoder, RobotMap.DIO2_LeftEncoder, true, CounterBase.EncodingType.k2X){{
            this.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
            this.setDistancePerPulse(((4.0*3.14159*(40.0/45.0))/100.0/12.0));
            this.start();            
        }};
    
    public int AUTO = 4;
    public int HIGH = 2;
    public int LOW = 5;
    
    int state;
    
    public float upShiftSpeedThreshold = 4.5f; 
    public float upShiftAccelerationThreshold = 5f;
    
    public float downShiftSpeedThreshold = 7.0f;
    public float downShiftAccelerationThreshold = 7.0f;
    public float minHighGearSpeed = 1.5f;
    
    public long shiftCooldownTime = 500; //in milliseconds like System.currentTimeMillis();
    
    boolean inHighGear;
    long lastShiftTime;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_GearShift());
    }
    
    public void setGear(boolean state)
    {
        solenoid_gear_shift.set(state);
        inHighGear = state;
        lastShiftTime = System.currentTimeMillis();
    }
    
    boolean isCooledOff() {
        return lastShiftTime - shiftCooldownTime > System.currentTimeMillis();
    }
    
    public void autoShift(){
        //don't shift while turning
        //don't shift if shifted too recently
        if (isCooledOff() & !OI.isTurning()) //both required to shift
        {
            if (speed() > upShiftSpeedThreshold & acceleration() > this.upShiftAccelerationThreshold) {
                setGear(true);
            }
            if (speed() < downShiftSpeedThreshold & acceleration() > this.downShiftAccelerationThreshold) {
                setGear(false);
            }
            if (speed() < minHighGearSpeed) {
                setGear(false);
            }
        }
    }
    
    double speed() {
        //wheel spinning should be slow, should't mess up auto shifiting 
        //filter heavily 
        return Math.abs(leftEncoder.getRate());
    }
    
    double acceleration() {
        //filter heavily
        //use accelerometer on robo rio
        return Math.abs(15);
    }
}
