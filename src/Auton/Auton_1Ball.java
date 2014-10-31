/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import subsystems.drive.C_AutoDrive;
import subsystems.pickup.C_ExtendArm;
import subsystems.shooter.C_Shoot;
/**
 *
 * @author liamcook
 * @author afiolmahon
 */
public class Auton_1Ball extends CommandGroup {
        
    public Auton_1Ball() {        
        addSequential(new C_ExtendArm());
        addSequential(new C_AutoDrive(11.5));
        addSequential(new C_Shoot());        
    }
}