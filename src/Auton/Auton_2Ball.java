/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auton;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import subsystems.drive.C_AutoDrive;
import subsystems.pickup.C_ExtendArm;
import subsystems.pickup.C_LoadBallOnBumper;
import subsystems.pickup.C_PickupBall;
import subsystems.pickup.C_PickupBallToBumper;
import subsystems.shooter.C_GoToShot;
import subsystems.shooter.C_Shoot;
/**
 *
 * @author liamcook
 * @author afiolmahon
 */
public class Auton_2Ball extends CommandGroup {
        float autonShotPower = 2.5f;
    public Auton_2Ball() {
        
        addParallel(new C_GoToShot(autonShotPower));
        addSequential(new C_ExtendArm());
        addSequential(new C_PickupBallToBumper());
        
        addSequential(new C_AutoDrive(11.5));
        
        addSequential(new C_ExtendArm());
        addSequential(new C_LoadBallOnBumper());
        addSequential(new C_ExtendArm()); //just to be sure
        addSequential(new C_Shoot());
        addSequential(new C_GoToShot(autonShotPower));
        
        addSequential(new C_PickupBall());
        addSequential(new C_ExtendArm()); //just to be sure
        
        addSequential(new C_Shoot());
        
    }
}