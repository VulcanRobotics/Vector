
package robot;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import subsystems.drive.C_GearShift;
import subsystems.drive.C_ResetGyro;
import subsystems.pickup.A_Eject_Ball;
import subsystems.pickup.A_PickupBall;
import subsystems.pickup.A_Roll_In;

import subsystems.shooter.A_ShooterFire;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //Driver Stick
        public Joystick driverStick = new Joystick(1);
        public Button Button_GearShift = new JoystickButton(driverStick, RobotMap.Button_GearShift);
    //opStick
        public Joystick opStick = new Joystick(2);
        public Button Button_ManualRaiseTension = new JoystickButton(opStick, RobotMap.Button_ManualRaiseTension);
        public Button Button_ManualLowerTension = new JoystickButton(opStick, RobotMap.Button_ManualLowerTension);
        public Button Button_Trigger = new JoystickButton(opStick, RobotMap.Button_Trigger);
        public Button Button_Pickup = new JoystickButton(opStick, RobotMap.Button_Pickup);
        public Button Button_Passball = new JoystickButton(opStick, RobotMap.Button_PassBall);
        public Button Button_ForceCollectorDown = new JoystickButton(opStick, RobotMap.Button_ForceCollectorDown);
        public Button Button_GyroReset = new JoystickButton(opStick, RobotMap.Button_GyroReset);
        public Button Button_ManualRoller = new JoystickButton(opStick, RobotMap.Button_ManualRoller);
    //Joy 3
        public Joystick controlPanel = new Joystick(3);
        public Button Button_ManualTensionMode = new JoystickButton(controlPanel, RobotMap.Button_ManualOrAuto);
        public Button Button_shotType = new JoystickButton(controlPanel, RobotMap.Button_ShootingOrTruss); //true is normal shot
        public Button Button_HighPower = new JoystickButton(controlPanel, RobotMap.Button_HighPower);
        static public Button Button_LowPower = new JoystickButton(controlPanel, RobotMap.Button_LowPower);
        public Button Button_Reload = new JoystickButton(controlPanel, RobotMap.Button_Reload);
        public Button Button_EnableManualShotTrim = new JoystickButton(controlPanel, RobotMap.Button_EnableManualShotTrim);
        public Button Button_ManualExtension = new JoystickButton(controlPanel, RobotMap.Button_ManualExtension);

        
            static public double trusPowerHigh = 2.4;
            static public double trusPowerMid = 2.2;
            static public double trusPowerLow = 1.75;
            static public double shotPowerHigh = 2.05;
            static public double shotPowerMid = 1.95;
            static public double shotPowerLow = 1.56;
            static public double fixValue;
// Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
            
    public OI (){
        //Driver
            Button_GearShift.whenPressed(new C_GearShift(true));
            Button_GearShift.whenReleased(new C_GearShift(false));
        //Operator 
            Button_Trigger.whileHeld(new A_ShooterFire());
            Button_Pickup.whileHeld(new A_PickupBall());
            Button_Passball.whileHeld(new A_Eject_Ball());
            Button_ManualRoller.whileHeld(new A_Roll_In());
            //Gyro
                Button_GyroReset.whenPressed(new C_ResetGyro());  
        //ControlPanel          //ControlPanel  
          
              //Shot tension values          
              //Shot tension values
    }
   
    static public double getShotPower(){
        
          if(CommandBase.oi.Button_shotType.get()){ //Normal Shot Values
            if(CommandBase.oi.Button_HighPower.get()){//high power shot
                fixValue = SmartDashboard.getNumber("longShotPower", shotPowerHigh);
                
            }else if(CommandBase.oi.Button_LowPower.get()){//low power shot
                fixValue = SmartDashboard.getNumber("shortShotPower", shotPowerLow);
               
            }else{//mid power shot
                fixValue = SmartDashboard.getNumber("middleShotPower", shotPowerMid);
                
            }
        }else{ //Truss shot values
            if(CommandBase.oi.Button_HighPower.get()){ //high power truss
                fixValue = SmartDashboard.getNumber("longTrussPower", trusPowerHigh);
            }else if(CommandBase.oi.Button_LowPower.get()){ //low power truss
                fixValue = SmartDashboard.getNumber("shortTrussPower", trusPowerLow);
            }else{ //mid power truss
                fixValue = SmartDashboard.getNumber("middleTrussPower", trusPowerMid);
            }
        }
        return fixValue;
      }
    
    static public boolean shouldExtentionsBeOut() {
        return !Button_LowPower.get();
    }
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

