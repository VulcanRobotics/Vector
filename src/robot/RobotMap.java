package robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {    
//Systems
    //Compressor
        public static final int DIO_Compressor = 1;
        public static final int Relay_Compressor = 1;
    //Drive
        //PWMS
            public static final int PWM_LeftDrive = 1;
            public static final int PWM_RightDrive = 2; 
        //Encoders
            public static final int DIO1_LeftEncoder = 6;
            public static final int DIO2_LeftEncoder = 7;
            public static final int DIO1_RightEncoder = 8;
            public static final int DIO2_RightEncoder = 9;
        //ETC
            public static final int Solenoid_Gear_Shift = 1; //true is low gear
            public static final int AI_Gyro = 2;
    //Pickup
        public static final int DIO_Ball_Detector = 12; //true if no ball
        public static final int Solenoid_Ball_Loader = 2; //false is in, true is out
        public static final int PWM_BallPickup = 4; //positive rolls out, negative rolls in
        public static final int DIO_Arm_Out = 4; //false is out
        public static final int Solenoid_Collector = 5; //hold ball in, false is down
    //Shooter
        public static final int DIO_Shooter_Down = 5; //true when shooter is low enough to be held by trigger.
        public static final int Solenoid_Trigger = 3; //true is fire/unlatch
        public static final int Solenoid_Extensions = 4; //true is out
        //Tension
            public static final int PWM_Tension = 3; //negative value raises tension, positive lowers
            public static final int AI_Tension_Potentiometer = 1;
            public static final int DIO_Top_Limit_Switch = 2; //Tension Screw Top Limit
            public static final int DIO_Bottom_Limit_Switch = 3; //Tension Screw Bottom Limit
//Control Systems    
    //Joystick 1 (Driver)
        //Buttons
        public static final int Button_GearShift = 1;//Shifts gears
        public static final int Button_xBoxShift = 6;
    //Joystick 2 (Operator)
        //Buttons
        public static final int Button_Trigger = 1; //Trigger
        public static final int Button_Pickup = 2; //activate pickup
        public static final int Button_PassBall = 3; //Backs ball out/passes
        public static final int Button_ManualRaiseTension = 4; //In manual mode raise tension
        public static final int Button_ManualRoller = 5; //Manual ball roll in
        public static final int Button_ManualLowerTension = 6; //In manual mode lower tension
        public static final int Button_GyroReset = 8; //Reset Gyro
        public static final int Button_ForceCollectorDown = 9; //if true force Collector down
        public static final int Button_EnableGyroSpin = 11; //Force Collector Up?
        
    //Joystick 3 (USB3)
        //Axis
            //axis 3 is used for tension trimming when manual shot trim is enabled
        //Buttons
        public static final int Button_ShootingOrTruss = 1; //True is Normal Shot
        public static final int Button_ManualOrAuto = 3; //False is Auto mode
        public static final int Button_HighPower = 4; //True is longShotPower/longTrussPower
        public static final int Button_LowPower = 5; //True is shortShotPower/shortTrussPower
        public static final int Button_ManualExtension = 7; //Controls extension in Manual Mode
        public static final int Button_EnableManualShotTrim = 8; //if true, value from axis 3 is used to trim the desired tension level
        public static final int Button_Reload = 9; //If True, reload
}
