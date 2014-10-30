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

        //Solenoids
            public Solenoid solenoid_trigger = new Solenoid(RobotMap.Solenoid_Trigger);
            public Solenoid solenoid_extensions = new Solenoid(RobotMap.Solenoid_Extensions);
        //Digital In

            public DigitalInput shooterDown = new DigitalInput(RobotMap.DIO_Shooter_Down);

            public double target;

    public void initDefaultCommand() {
        initSolenoids();
        setDefaultCommand(new CM_ShooterMain());
        
    }
    
    public void initSolenoids(){ //Initializes our solenoids so we know they are in the correct state.
        solenoid_trigger.set(false); //false is closed
        solenoid_extensions.set(false); //false is retracted
    }

    //extension state setting goes through this to make the extension switch function
    boolean forceExtend = false;//extension switch sets this boolean which forces the extensions out when true
    void setExtensions(boolean state){
        solenoid_extensions.set(state);
    }
    public void extensionsOut() {
        setExtensions(true);
    }
    public void extensionsIn() {
        setExtensions(false);
    }
    
    boolean openLatch() {
        if (!CommandBase.tension.isTensionDangerous() | CommandBase.pickup.isArmOut()){
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
        //System.out.println(shooterDown.get());
        return shooterDown.get();
    }
}