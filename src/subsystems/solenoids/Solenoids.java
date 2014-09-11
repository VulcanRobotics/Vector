/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.solenoids;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class Solenoids extends Subsystem {
    Solenoid shift = new Solenoid(RobotMap.Solenoid_Gear_Shift);


    public void initDefaultCommand() {
        setDefaultCommand(new subsystems.solenoids.gearShift());
    }
    
    public void gearing(){
        shift.set(true);
    }
}
