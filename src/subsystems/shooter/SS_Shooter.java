/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;


/**
 *
 * @author afiol-mahon
 */
public class SS_Shooter extends Subsystem {
    
    //Tension
        public O_TenModule tenModule = new O_TenModule(RobotMap.PWM_Tension);
        public double uncockTension = 0.15;
        //Shot tension values
            public double trusPowerHigh = 2.6;
            public double trusPowerMid = 2.2;
            public double trusPowerLow = 1.75;
            public double shotPowerHigh = 2.05;
            public double shotPowerMid = 1.95;
            public double shotPowerLow = 1.56;
        //PID Controller
            double Kp = 4.500;
            double Ki = 0.010;
            double Kd = 1.000;
            public PIDController tensionPID = new PIDController(Kp, Ki, Kd, tenModule.tenPot, tenModule){{
                this.setAbsoluteTolerance(0.2);
            }};
    //Non-Tension Systems
        //Solenoids
            public Solenoid solenoid_trigger = new Solenoid(RobotMap.Solenoid_Trigger);
            public Solenoid solenoid_extensions = new Solenoid(RobotMap.Solenoid_Extensions);
        //Digital In
            
            public DigitalInput shooterDown = new DigitalInput(RobotMap.DIO_Shooter_Down);


    public void initDefaultCommand() {
        initSolenoids();
        setDefaultCommand(new CM_ShooterMain());
    }
    
    public void initSolenoids(){ //Initializes our solenoids so we know they are in the correct state.
        solenoid_trigger.set(false); //false is closed
        solenoid_extensions.set(false); //false is retracted
    }
    
    public void syncDashboard(){ //Publish Subsystem information.
        //Tension
            SmartDashboard.putBoolean("Top Limit Switch", tenModule.Top_Limit_Switch.get());
            SmartDashboard.putBoolean("Bottom Limit Switch", tenModule.Bottom_Limit_Switch.get());
            SmartDashboard.putBoolean("Upper Soft Limit", !(tenModule.tenPot.pidGet() >= tenModule.tenPotMIN));
            SmartDashboard.putBoolean("Lower Soft Limit", !(tenModule.tenPot.pidGet() <= tenModule.tenPotMAX));
            SmartDashboard.putNumber("Tension Potentiometer", tenModule.tenPot.pidGet());
            SmartDashboard.putNumber("Target", tensionPID.getSetpoint());
            SmartDashboard.putBoolean("Shooter Down", shooterDown.get());
    }

    void manualCheck() { //Disables PIDController if we are in manual tension mode.
        if(CommandBase.oi.Button_ManualTensionMode.get()){
            tensionPID.disable();
        }else{
            tensionPID.enable();
        }
    }
    
    public double configureShot(){
        double fixValue;
        if(CommandBase.oi.Button_shotType.get()){ //Normal Shot Values
            if(CommandBase.oi.Button_HighPower.get()){//high power shot
                fixValue = SmartDashboard.getNumber("longShotPower", shotPowerHigh);
                CommandBase.shooter.setExtension(true);
            }else if(CommandBase.oi.Button_LowPower.get()){//low power shot
                fixValue = SmartDashboard.getNumber("shortShotPower", shotPowerLow);
                CommandBase.shooter.setExtension(false);
            }else{//mid power shot
                fixValue = SmartDashboard.getNumber("middleShotPower", shotPowerMid);
                CommandBase.shooter.setExtension(true);
            }
        }else{ //Truss shot values
            if(CommandBase.oi.Button_HighPower.get()){ //high power truss
                fixValue = SmartDashboard.getNumber("longTrussPower", trusPowerHigh);
                CommandBase.shooter.setExtension(true);
            }else if(CommandBase.oi.Button_LowPower.get()){ //low power truss
                fixValue = SmartDashboard.getNumber("shortTrussPower", trusPowerLow);
                CommandBase.shooter.setExtension(false);
            }else{ //mid power truss
                fixValue = SmartDashboard.getNumber("middleTrussPower", trusPowerMid);
                CommandBase.shooter.setExtension(true);
            }
        }
        if(CommandBase.oi.Button_EnableManualShotTrim.get()){
           double manualTrim = (((int)(CommandBase.oi.controlPanel.getZ()*5))/5)*0.1; //multiply Z axis of controlpanel by 5, convert result to nearest integer, divide by five, and get 10% of that
           SmartDashboard.putNumber("Adjust", manualTrim);
           return fixValue+manualTrim; //target is our fixed value offset by our manaulTrim value
        }else{
            return fixValue; //if manualTrim is disabled, just return the fixed value
        }
    }
   
   
    //extension state setting goes through this to make the extension switch function
    boolean forceExtend = false;//extension switch sets this boolean which forces the extensions out when true
    void setExtension(boolean state){
        solenoid_extensions.set(state);
    }
    public void extensionsOut() {
        setExtension(true);
    }
    public void extensionsIn() {
        setExtension(false);
    }
    
    boolean openLatch() {
        if (!tenModule.isTensionDangerous() | CommandBase.pickup.isArmOut()){
            solenoid_trigger.set(true);
            return true;
        }else{
            return false;
        }
    }
    
    void closeLatch() {
        solenoid_trigger.set(false);
    }
    
    boolean isShooterDown(){
        return shooterDown.get();
    }
    
}