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
import edu.wpi.first.wpilibj.AnalogChannel;

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
    
    public AnalogChannel accelerometer = new AnalogChannel(3);
   
    public int AUTO = 4;
    public int HIGH = 2;
    public int LOW = 5;
    
    int state;
    
    public float upShiftSpeedThreshold = 4.5f; 
    public float upShiftAccelerationThreshold = 0.1f;
    
    public float downShiftSpeedThreshold = 5f;
    public float downShiftAccelerationThreshold = 0.1f;
    public float minHighGearSpeed = 4f;
    
    public float joystickPowerThreshold = 0.8f;
    
    public long shiftCooldownTime = 500; //in milliseconds like System.currentTimeMillis();
    
    
    boolean inHighGear;
    long lastShiftTime = 0;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_GearShift());
    }
    
    public void setGear(boolean state)
    {
        solenoid_gear_shift.set(!state);
        if (inHighGear == !state & isCooledOff())
        {
            //no shifting happened
        }
        else
        {
            //shift occured
            System.out.println("Shifted to: " + state);
            lastShiftTime = System.currentTimeMillis();
            inHighGear = !state;
        }
    }
    
    boolean isCooledOff() {
        return lastShiftTime + shiftCooldownTime < System.currentTimeMillis();
    }
    
    void updateShiftThreshold(){
        upShiftSpeedThreshold = (float)((OI.controlPanel.getX() +1) * 3);
        //System.out.println("threshold: " + upShiftSpeedThreshold);
    }
    
    public void autoShift(){
        //don't shift while turning
        //don't shift if shifted too recently
        System.out.println("autoshifting");
        if (isCooledOff() & !OI.isTurning()) //both required to shift
        {
            System.out.println("threshold: " + upShiftSpeedThreshold + " speed: " + speed());
            if (speed() > upShiftSpeedThreshold & acceleration() > this.upShiftAccelerationThreshold) {
                setGear(true);
            }
            //if (speed() < downShiftSpeedThreshold & acceleration() < this.downShiftAccelerationThreshold & Math.abs(OI.driverStick.getY()) > 0.75) {
           //     setGear(false);
           // }
            if (speed() < minHighGearSpeed  & Math.abs(OI.driverStick.getY()) > joystickPowerThreshold & acceleration() < downShiftAccelerationThreshold) {
                setGear(false);
            }
        }
    }
    
    double speed() {
        //wheel spinning should be slow, should't mess up auto shifiting 
        //filter heavily 
       // System.out.println(Math.abs(leftEncoder.getRate()));
        return Math.abs(leftEncoder.getRate());
        
    }
    
    double acceleration() {
       //  System.out.println(accelerometer.getAverageVoltage() - 1.5);
        return Math.abs(accelerometer.getAverageVoltage() - 1.5);
               
    }
}
