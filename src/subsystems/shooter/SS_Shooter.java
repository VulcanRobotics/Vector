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
        O_TenModule tenModule = new O_TenModule(RobotMap.PWM_Tension);
        //PID Controller
            double Kp = 4.500;
            double Ki = 0.010;
            double Kd = 1.000;
            PIDController tensionPID = new PIDController(Kp, Ki, Kd, tenModule.tenPot, tenModule);
    //Non-Tension Systems
        //Solenoids
            Solenoid solenoid_trigger = new Solenoid(RobotMap.Solenoid_Trigger);
            Solenoid solenoid_collector = new Solenoid(RobotMap.Solenoid_Collector);
            Solenoid solenoid_extensions = new Solenoid(RobotMap.Solenoid_Extensions);
        //Digital In
        DigitalInput BallDetector = new DigitalInput(RobotMap.DIO_Ball_Detector);
        DigitalInput shooterDown = new DigitalInput(RobotMap.DIO_Shooter_Down);
    
    public void initDefaultCommand() {
        initSolenoids();
        setDefaultCommand(new C_ShooterMain());
    }
    
    public void initSolenoids(){
        solenoid_trigger.set(false); //false is closed
        solenoid_collector.set(false);
        solenoid_extensions.set(false);
    }
    
    public void syncDashboard(){
        //Tension
        SmartDashboard.putBoolean("Top Limit Switch", tenModule.Top_Limit_Switch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch", tenModule.Bottom_Limit_Switch.get());
        SmartDashboard.putBoolean("Upper Soft Limit", !(tenModule.tenPot.pidGet() >= tenModule.tenPotMIN));
        SmartDashboard.putBoolean("Lower Soft Limit", !(tenModule.tenPot.pidGet() <= tenModule.tenPotMAX));
        SmartDashboard.putNumber("Tension Potentiometer", tenModule.tenPot.pidGet());
        //Other Systems
        SmartDashboard.putBoolean("Ball Ready", !BallDetector.get());
    }

    void manualCheck() {
        if(CommandBase.oi.Button_ManualTensionMode.get()){
            tensionPID.disable();
        }else{
            tensionPID.enable();
        }
    }
}