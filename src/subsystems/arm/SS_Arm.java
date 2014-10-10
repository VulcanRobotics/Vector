/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.arm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;
/**
 *
 * @author afiolmahon
 */
public class SS_Arm extends Subsystem {
    
    public double BallPickup_Speed = 0.7;
    Solenoid solenoid_ball_loader = new Solenoid(RobotMap.Solenoid_Ball_Loader);
    Talon BallPickup = new Talon(RobotMap.PWM_BallPickup);
    public DigitalInput Arm_Out = new DigitalInput(RobotMap.DIO_Arm_Out);

    public void initDefaultCommand() {
        solenoid_ball_loader.set(false); //false is in, true is out
    }
    
    public void extend(){
        solenoid_ball_loader.set(true);
    }
    public void retract(){
        solenoid_ball_loader.set(false);
    }
    
    public void syncDashboard(){
        SmartDashboard.putBoolean("Arm Out", Arm_Out.get());
    }
}
