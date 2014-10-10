/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.air;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class SS_AirSystem extends Subsystem {
    public Compressor compressor = new Compressor(RobotMap.DIO_Compressor, RobotMap.Relay_Compressor);
    
    public void initDefaultCommand() {
            compressor.start();
    }
}
