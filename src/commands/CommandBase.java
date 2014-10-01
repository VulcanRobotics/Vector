package commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.OI;
import subsystems.air.SS_AirSystem;
import subsystems.arm.SS_Arm;
import subsystems.drive.SS_Drive;
import subsystems.shooter.SS_Shooter;
import subsystems.tension.SS_Tension;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author afiolmahon
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    public static SS_Drive drive = new SS_Drive();
    public static SS_AirSystem airSystem = new SS_AirSystem();
    public static SS_Arm arm = new SS_Arm();
    public static SS_Shooter shooter = new SS_Shooter();
    public static SS_Tension tension = new SS_Tension();
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(exampleSubsystem);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
