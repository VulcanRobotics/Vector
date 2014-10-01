/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;

/**
 *
 * @author afiolmahon
 */
public class SS_Shooter extends Subsystem {
    Solenoid solenoid_trigger = new Solenoid(RobotMap.Solenoid_Trigger);
    Solenoid solenoid_collector = new Solenoid(RobotMap.Solenoid_Collector);
    Solenoid solenoid_extensions = new Solenoid(RobotMap.Solenoid_Extensions);
    
    DigitalInput BallDetector = new DigitalInput(RobotMap.DIO_Ball_Detector);
    

    public void initDefaultCommand() {
        setDefaultCommand(new C_Shooter_Main());
        solenoid_trigger.set(false);
        solenoid_collector.set(false);
        solenoid_extensions.set(false);
    }
    
    public void syncDashboard(){
        SmartDashboard.putBoolean("Ball Ready", !BallDetector.get());
    }
}
