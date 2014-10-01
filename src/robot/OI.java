
package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import subsystems.drive.C_GearShift;
import subsystems.tension.C_Tension_ManualRaise;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public Joystick driverStick = new Joystick(1);
    Button Button_GearShift = new JoystickButton(driverStick, RobotMap.Button_GearShift);
    
    Joystick opStick = new Joystick(2);
    public Button Button_ManualRaiseTension = new JoystickButton(opStick, RobotMap.Button_ManualRaiseTension);
    public Button Button_ManualLowerTension = new JoystickButton(opStick, RobotMap.Button_ManualLowerTension);
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    public OI (){
        Button_GearShift.whenPressed(new C_GearShift(true));
        Button_GearShift.whenReleased(new C_GearShift(false));
        
        Button_ManualRaiseTension.whenPressed(new C_Tension_ManualRaise(0.7));
        //Button_ManualRaiseTension.whenReleased(new C_Tension_Manual(0.0));
        
        Button_ManualLowerTension.whenPressed(new C_Tension_ManualRaise(-0.7));
        //Button_ManualLowerTension.whenReleased(new C_Tension_Manual(0.0));
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

