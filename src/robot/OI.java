
package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import subsystems.shooter.C_Pass;
import subsystems.shooter.C_Pickup;
import subsystems.drive.C_GearShift;
import subsystems.shooter.C_ForceCollectorDown;
import subsystems.shooter.C_ShooterFire;
import subsystems.shooter.C_Tension_ManualLower;
import subsystems.shooter.C_Tension_ManualRaise;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

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
    
    //Joy 3
    public Joystick controlPanel = new Joystick(3);
    public Button Button_ManualTensionMode = new JoystickButton(controlPanel, RobotMap.Button_ManualOrAuto);
    public Button Button_shotType = new JoystickButton(controlPanel, RobotMap.Button_ShootingOrTruss); //true is normal shot
    public Button Button_HighPower = new JoystickButton(controlPanel, RobotMap.Button_HighPower);
    public Button Button_LowPower = new JoystickButton(controlPanel, RobotMap.Button_LowPower);
    public Button Button_Reload = new JoystickButton(controlPanel, RobotMap.Button_Reload);
    public Button Button_EnableManualShotTrim = new JoystickButton(controlPanel, RobotMap.Button_EnableManualShotTrim);

    
// Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    public OI (){
        Button_GearShift.whenPressed(new C_GearShift(true));
        Button_GearShift.whenReleased(new C_GearShift(false));
                
        Button_ManualRaiseTension.whenPressed(new C_Tension_ManualRaise(0.7));        
        Button_ManualLowerTension.whenPressed(new C_Tension_ManualLower(-0.7));   
        
        Button_Trigger.whenPressed(new C_ShooterFire());
        Button_Pickup.whenPressed(new C_Pickup());
        Button_Passball.whenPressed(new C_Pass());
        Button_ForceCollectorDown.whenPressed(new C_ForceCollectorDown());
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

