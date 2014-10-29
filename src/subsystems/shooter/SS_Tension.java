/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.shooter;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import robot.RobotMap;

/**
 *
 * @author 1218
 */
public class SS_Tension extends PIDSubsystem {

    public double tenPotMAX = 2.55;
    public double tenPotMIN = 0.2;
    
             //PID Controller
    private static final double Kp = 4.500;
    private static final double Ki = 0.010;
    private static final double Kd = 1.000;


    public DigitalInput Top_Limit_Switch = new DigitalInput(RobotMap.DIO_Top_Limit_Switch);
    public DigitalInput Bottom_Limit_Switch = new DigitalInput(RobotMap.DIO_Bottom_Limit_Switch);
    
    public AnalogChannel tenPot = new AnalogChannel(RobotMap.AI_Tension_Potentiometer);
    
    
     private Talon motor = new Talon(RobotMap.PWM_Tension);
    // Initialize your subsystem here
    public SS_Tension() {
        super("SS_Tension", Kp, Ki, Kd);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //       
        setTarget(2);
        setPercentTolerance(5);
        disable(); //- Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return getTensionPot();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        boolean isGoingUp = output > 0;
        System.out.println("pid target: "+ this.getSetpoint());
        if (isGoingUp) {
           moveUp(output);
        }
        else
        {
           moveDown(output);
        }
    }
    void moveDown(double speed)
    {
        speed = Math.abs(speed);
        if(!topSoftLimit() | !Top_Limit_Switch.get())
           {
                motor.set(-speed*0.15);
           }
           else
           {
               motor.set(0);
           }
    }
    void moveUp(double speed){
        speed = Math.abs(speed);
        if(!bottomSoftLimit() | !Bottom_Limit_Switch.get())
           {
                motor.set(speed*0.15);
           }
           else
           {
               motor.set(0);
           }
    }
    
    
     void stopMotor(){
         motor.set(0);
     }
    boolean bottomSoftLimit(){
        return getTensionPot() < tenPotMAX;
    }
    boolean topSoftLimit(){
        return getTensionPot() > tenPotMIN;
    }
    
    boolean setTarget(double target) {
        enable();
        setSetpoint(target);
        return true;
    }
    
    void cock() {
       setTarget(tenPotMIN);
       enable();
    }    
    
    boolean isTensionDangerous()
    {
        return getTensionPot() > 1.5;
    }
 
    double getTensionPot(){
        System.out.println("tension is: " + (5.0 - tenPot.pidGet()));
        return 5.0 - tenPot.pidGet();
    }
   
}
