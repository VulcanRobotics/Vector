/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.pickup;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 * @author liamcook
 */
public class SS_Pickup extends Subsystem {    

    public Solenoid solenoid_Ball_Loader = new Solenoid(RobotMap.Solenoid_Ball_Loader);
    public Solenoid solenoid_collector = new Solenoid(RobotMap.Solenoid_Collector);
    
    public DigitalInput ballDetector = new DigitalInput(RobotMap.DIO_Ball_Detector);
    
    public DigitalInput Arm_Out = new DigitalInput(RobotMap.DIO_Arm_Out);
    
    public double BallPickup_Speed = 0.7;
    public Talon ballPickup = new Talon(RobotMap.PWM_BallPickup);//negative value is roll in
        
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //controlling arm
    public void armOut() {
        solenoid_Ball_Loader.set(true);
    }
    
    public void armIn() {
        solenoid_Ball_Loader.set(false);
    }
    
    //controlling collector
    public void collectorUp() {
        solenoid_collector.set(true);
    }
    
    public void collectorDown() {
        solenoid_collector.set(false);
    }
    
    //rolling ball in/out
    public void stopRollers() {
        ballPickup.set(0);
    }
    public void rollBallIn() {
        ballPickup.set(-(Math.abs(BallPickup_Speed)));
    }
    
    
    public void rollBallOut() {
        ballPickup.set(Math.abs(BallPickup_Speed));
    }
    
    public void rollBallIn(float speed) {
        ballPickup.set(-(Math.abs(speed)));
    }
    
    public void rollBallOut(float speed) {
        ballPickup.set(Math.abs(speed));
    }
    
    //sensing
    /**
     * Returns true if arm is out.
     */
    public boolean isArmOut() {
        return !Arm_Out.get();
    }
    
    /**
     * Returns true if ball is held.
     */
    public boolean hasBall() {
        return !ballDetector.get();
    }
    
    public void defaultState(){
        armIn();
        collectorUp();
        stopRollers();
    }

}
