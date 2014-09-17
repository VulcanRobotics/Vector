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
public class AirSystem extends Subsystem {
    Compressor compressor = new Compressor(RobotMap.DIO_Compressor, RobotMap.Relay_Compressor);
    Solenoid solenoid_shift = new Solenoid(RobotMap.Solenoid_Gear_Shift);
    
    public void initDefaultCommand() {
            setDefaultCommand(new AirSystem_idle());
            solenoid_shift.set(true);
            compressor.start();
    }
    
    public void compress(){
        compressor.start();
    }
}
