/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.tension;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class O_TenModule implements PIDOutput{

    //Constants
    public double tenPotMAX = 2.55;
    public double tenPotMIN = 0.2;
    
    public double trusPowerHigh = 2.6;
    public double trusPowerMid = 2.2;
    public double trusPowerLow = 1.75;
    
    public double shotPowerHigh = 2.05;
    public double shotPowerMid = 1.95;
    public double shotPowerLow = 1.56;
    
    //Inputs
    public DigitalInput Top_Limit_Switch = new DigitalInput(RobotMap.DIO_Top_Limit_Switch);
    public DigitalInput Bottom_Limit_Switch = new DigitalInput(RobotMap.DIO_Bottom_Limit_Switch);
    
    //PIDSource
    public O_TenPotPIDSource tenPot = new O_TenPotPIDSource(5.00, RobotMap.AI_Tension_Potentiometer);
    
    //Output
    private Talon motor;
    
    //constructor
    public O_TenModule(int channel){
        motor = new Talon(channel);
    }
    
    //output to motor, implemented by PIDOutput
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

    public void setManualTension(double speed) {
        if(CommandBase.oi.Button_ManualTensionMode.get()){
            setTension(speed);
        }
    }
    
    public double getTensionTargetSelect(){
        if(CommandBase.oi.Button_shotType.get()){ //Normal Shot Values
            if(CommandBase.oi.Button_HighPower.get()){//high power shot
                return SmartDashboard.getNumber("longShotPower", shotPowerHigh);
            }else if(CommandBase.oi.Button_LowPower.get()){//low power shot
                return SmartDashboard.getNumber("shortShotPower", shotPowerLow);
            }else{//mid power shot
                return SmartDashboard.getNumber("middleShotPower", shotPowerMid);
            }
        }else{ //Truss shot values
            if(CommandBase.oi.Button_HighPower.get()){ //high power truss
                return SmartDashboard.getNumber("longTrussPower", trusPowerHigh);
            }else if(CommandBase.oi.Button_LowPower.get()){ //low power truss
                return SmartDashboard.getNumber("shortTrussPower", trusPowerLow);
            }else{ //mid power truss
                return SmartDashboard.getNumber("middleTrussPower", trusPowerMid);
            }
        }
    }
}
