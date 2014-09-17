package robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 //PWM
    public static final int PWM_LeftDrive = 1;
    public static final int PWM_RightDrive = 2;
    public static final int PWM_Tension = 3;
    public static final int PWM_BallPickup = 4;
    
//Systems
    //Compressor
        public static final int DIO_Compressor = 1;
        public static final int Relay_Compressor = 1;
    //left Encoder
        public static final int DigitalModule_LeftEncoder = 1;
        public static final int DIO1_LeftEncoder = 6;
        public static final int DIO2_LeftEncoder = 7;
    //Right Encoder
        public static final int DigitalModule_RightEncoder = 1;
        public static final int DIO1_RightEncoder = 8;
        public static final int DIO2_RightEncoder = 9;
    //Limit Switches
        public static final int DIO_Top_Limit_Switch = 2;
        public static final int DIO_Bottom_Limit_Switch = 3;
    //ETC
        public static final int DIO_Arm_Out = 4; //reed switch
        public static final int DIO_Shooter_Down_New = 5; //limit switch
        public static final int DIO_Arm_Half_Out = 11; //limit
        public static final int DIO_Shooter_Down_Old = 10;//Depreciated?
        public static final int DIO_Ball_Detector = 12; //infared thing
    //Tension Potentiometer
        public static final int AnalogModule_Tension_Potentiometer = 1;
        public static final int AI_Tension_Potentiometer = 1;
    //Gyro
        public static final int AnalogModule_Gyro = 1;
        public static final int AI_Gyro = 2;
    //Solenoids
        public static final int Solenoid_Gear_Shift = 1;
        public static final int Solenoid_Ball_Loader = 2;
        public static final int Solenoid_Trigger = 3;
        public static final int Solenoid_Extensions = 4;
        public static final int Solenoid_Collector = 5;
        
//Control Systems    
    //Joystick 1 (USB1)
        //Buttons
        public static final int Button_GearShift = 11;
    //Joystick 2 (USB2)
        //Buttons
        
    //Joystick 3 (USB3)
        //Buttons
            
}
