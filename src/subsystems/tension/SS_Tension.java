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
    
    Talon motorTension = new Talon(RobotMap.PWM_Tension);
    AnalogChannel tensionPot = new AnalogChannel(RobotMap.AI_Tension_Potentiometer);
    DigitalInput Top_Limit_Switch = new DigitalInput(RobotMap.DIO_Top_Limit_Switch);
    DigitalInput Bottom_Limit_Switch = new DigitalInput(RobotMap.DIO_Bottom_Limit_Switch);

    public void initDefaultCommand() {
        setDefaultCommand(new C_Tension_Main());
    }
    
     public void syncDashboard(){
        SmartDashboard.putBoolean("Top Limit Switch", Top_Limit_Switch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch", Bottom_Limit_Switch.get());
        SmartDashboard.putBoolean("Upper Soft Limit", tensionPot.getVoltage()-5>0.15);
    }
     
    public void tensionChange(double power){
        if(!Bottom_Limit_Switch.get() && !Top_Limit_Switch.get()){
            motorTension.set(power);
        }else if(Bottom_Limit_Switch.get() && power>0){
            motorTension.set(power);
        }else if(Top_Limit_Switch.get() && power < 0){
            motorTension.set(power);
        }
    }
    
    public void tensionRangeCheck(){
        if(Bottom_Limit_Switch.get() && motorTension.get() < 0){
            motorTension.set(0.0);
        }else if(Top_Limit_Switch.get() && motorTension.get() > 0){
            motorTension.set(0.0);
        }
    }
}
