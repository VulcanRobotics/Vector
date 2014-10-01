/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.tension;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class SS_Tension extends Subsystem {
    
    //Constants
    double tenPotMAX = 2.55;
    double tenPotMIN = 0.15;
    //Motors
    Talon motorTension = new Talon(RobotMap.PWM_Tension);
    //Sensors
    AnalogChannel tensionPot = new AnalogChannel(RobotMap.AI_Tension_Potentiometer);
    DigitalInput Top_Limit_Switch = new DigitalInput(RobotMap.DIO_Top_Limit_Switch);
    DigitalInput Bottom_Limit_Switch = new DigitalInput(RobotMap.DIO_Bottom_Limit_Switch);

    public void initDefaultCommand() {
        setDefaultCommand(new C_Tension_Main());
    }
    
    public double getTenPot(){
        return 5.0-tensionPot.getVoltage();
    }
    
     public void syncDashboard(){
        SmartDashboard.putBoolean("Top Limit Switch", Top_Limit_Switch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch", Bottom_Limit_Switch.get());
        SmartDashboard.putBoolean("Upper Soft Limit", getTenPot() >= tenPotMAX);
        SmartDashboard.putBoolean("Lower Soft Limit", getTenPot() <= tenPotMIN);
    }
     
    //All calls to motorTension should be handled through this class!
    public void setTension(double power){
        int T_limit = 0;
        int L_limit = 0;
        if(getTenPot() > tenPotMIN && !Bottom_Limit_Switch.get()){
            L_limit = -1;
        }
        if(getTenPot() < tenPotMAX && !Top_Limit_Switch.get()){
            T_limit = 1;
        }
        if(power <= T_limit && power >= L_limit){
        motorTension.set(power);
        }else if(power > T_limit){
            motorTension.set(T_limit);
        }else if(power < L_limit){
            motorTension.set(L_limit);
        }
    }
    
    public void tensionHardRangeCheck(){
        if(motorTension.get() < 0){
            if(Bottom_Limit_Switch.get() | getTenPot() < tenPotMIN ){
                motorTension.set(0.0);
            }
        }else if(Top_Limit_Switch.get() | getTenPot() > tenPotMAX){
            if(Top_Limit_Switch.get() && motorTension.get() > 0){
             motorTension.set(0.0);
            }
        }
    }
}
