/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.tension;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class O_TenModule implements PIDOutput{

    //Constants
    //Constants
    public double tenPotMAX = 2.55;
    public double tenPotMIN = 0.2;
    
    public DigitalInput Top_Limit_Switch = new DigitalInput(RobotMap.DIO_Top_Limit_Switch);
    public DigitalInput Bottom_Limit_Switch = new DigitalInput(RobotMap.DIO_Bottom_Limit_Switch);
    
    public O_TenPotPIDSource tenPot = new O_TenPotPIDSource(5.00, RobotMap.AI_Tension_Potentiometer);

    private Talon motor;
    public O_TenModule(int channel){
        motor = new Talon(channel);
    }
    
    public void pidWrite(double output) {
        setTension(-output);
    }
    //All calls to motorTension should be handled through this method!
    public void setTension(double power){
        int T_limit = 0;
        int L_limit = 0;
        if(tenPot.pidGet() < tenPotMAX && !Bottom_Limit_Switch.get()){
            L_limit = -1;
        }
        if(tenPot.pidGet() > tenPotMIN && !Top_Limit_Switch.get()){
            T_limit = 1;
        }
        if(power <= T_limit && power >= L_limit){
            motor.set(power);
        }else if(power > T_limit){
            motor.set(T_limit);
        }else if(power < L_limit){
            motor.set(L_limit);
        }
    }
    
     public void tensionRangeCheck(){
        if(motor.get() < 0){
            if(Bottom_Limit_Switch.get() | tenPot.pidGet()> tenPotMAX ){
                motor.set(0.0);
            }
        }else if(motor.get() > 0){
            if(Top_Limit_Switch.get() | tenPot.pidGet()< tenPotMIN){
             motor.set(0.0);
            }
        }
    }   
    
}
