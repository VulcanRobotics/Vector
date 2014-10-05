/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.tension;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;

/**
 *
 * @author afiol-mahon
 */
public class SS_Tension extends Subsystem {
    
    O_TenModule tenModule = new O_TenModule(RobotMap.PWM_Tension);
    
    //PID Controller
    double Kp = 4.500;
    double Ki = 0.010;
    double Kd = 1.000;
    PIDController tensionPID = new PIDController(Kp, Ki, Kd, tenModule.tenPot, tenModule);
    public void initDefaultCommand() {
        setDefaultCommand(new C_Tension_Main());
        tensionPID.setSetpoint(1.0);
    }
    
     public void syncDashboard(){
        SmartDashboard.putBoolean("Top Limit Switch", tenModule.Top_Limit_Switch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch", tenModule.Bottom_Limit_Switch.get());
        SmartDashboard.putBoolean("Upper Soft Limit", !(tenModule.tenPot.pidGet() >= tenModule.tenPotMIN));
        SmartDashboard.putBoolean("Lower Soft Limit", !(tenModule.tenPot.pidGet() <= tenModule.tenPotMAX));
        SmartDashboard.putNumber("Tension Potentiometer", tenModule.tenPot.pidGet());
    }
     
    public boolean isManualMode(){
        return CommandBase.oi.Button_ManualOrAuto.get();
    }
}