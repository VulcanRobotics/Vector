/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.air;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class SS_AirSystem extends Subsystem {
    Compressor compressor = new Compressor(RobotMap.DIO_Compressor, RobotMap.Relay_Compressor);
    //Solenoids
        Solenoid solenoid_gear_shift = new Solenoid(RobotMap.Solenoid_Gear_Shift);
        Solenoid solenoid_ball_loader = new Solenoid(RobotMap.Solenoid_Ball_Loader);
        Solenoid solenoid_trigger = new Solenoid(RobotMap.Solenoid_Trigger);
        Solenoid solenoid_extensions = new Solenoid(RobotMap.Solenoid_Extensions);
        Solenoid solenoid_collector = new Solenoid(RobotMap.Solenoid_Collector);
    
    public void initDefaultCommand() {
            initSolenoids();
            compressor.start();
            setDefaultCommand(new C_AirSystem_idle());
    }
    
    public void initSolenoids(){ //This method sets the default values for all of the solenoids being used on the robot.
        solenoid_gear_shift.set(false); //true is low gear
        solenoid_ball_loader.set(false); //false is in, true is out
        solenoid_trigger.set(false);
        solenoid_extensions.set(false);
        solenoid_collector.set(false); //true is up, false is down
    }
    
    private boolean gearShiftState = false;
    public void toggleGearshift(){
        if(gearShiftState == false){
            gearShiftState = true;
            
        }else{
            gearShiftState = false;
        }
        solenoid_gear_shift.set(gearShiftState);
    }
}
