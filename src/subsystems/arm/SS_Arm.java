/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.arm;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;
/**
 *
 * @author afiolmahon
 */
public class SS_Arm extends Subsystem {
    Solenoid solenoid_ball_loader = new Solenoid(RobotMap.Solenoid_Ball_Loader);

    public void initDefaultCommand() {
        solenoid_ball_loader.set(false); //false is in, true is out

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
