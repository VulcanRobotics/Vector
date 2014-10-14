/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author afiolmahon
 */
public class O_DrivePIDOutput implements PIDOutput{

    public void pidWrite(double d) {
        CommandBase.drive.chassis.arcadeDrive(-d, 0);
    }
    
}
