/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auton;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import subsystems.drive.C_AutoDrive;
import subsystems.shooter.C_FindShortTension;
import subsystems.shooter.C_WaitForArmOut;

/**
 *
 * @author afiolmahon
 */
public class CG_Auton1 extends CommandGroup {
    
    double driveLength = 11;
    
    public CG_Auton1() {
        addSequential(new C_AutoDrive(driveLength, 10));
        addSequential(new C_FindShortTension());
        CommandBase.shooter.solenoid_collector.set(true);
        System.out.println("setCollector");
        CommandBase.shooter.solenoid_Ball_Loader.set(true);
        System.out.println("setBallLoader");
        CommandBase.shooter.solenoid_extensions.set(false);
        System.out.println("setExtensions");
        addSequential(new C_WaitForArmOut());
        CommandBase.shooter.solenoid_trigger.set(true);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
