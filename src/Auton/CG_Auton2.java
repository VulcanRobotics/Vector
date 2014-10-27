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

/**
 *
 * @author afiolmahon
 */
public class CG_Auton2 extends CommandGroup {
        
    public CG_Auton2() {
        addSequential(new PreDrive());
        addSequential(new C_AutoDrive(7.0));
        addSequential(new PostDrive());
    }
    
    class PreDrive extends CommandBase{
        
        int state = 0;
        
        protected void initialize() {
        }

        protected void execute() {
            System.out.println("Auton2 State[PreDrive]: "+state);
            if(state == 0){
                shooter.extendArm();
                shooter.tensionPID.setAbsoluteTolerance(0.1);
                shooter.tensionPID.setSetpoint(shooter.shotPowerHigh);
                shooter.tensionPID.enable();
                state += 1;
            }
            else if(state == 1){
                state += shooter.Arm_Out.get() ? 1 : 0;
            }else if(state == 2){
                shooter.BallPickup.set(-1.0);
                Timer.delay(1.0);
                shooter.BallPickup.set(-0.1);
                state +=1;
            }
        }

        protected boolean isFinished() {
            return state == 3;
        }

        protected void end() {
        }

        protected void interrupted() {
        }
    
    }
    class PostDrive extends CommandBase{
        
        int state = 0;
        
        protected void initialize() {
        }
        
        protected void execute() {
            System.out.println("Auton2 State[PostDrive]: "+state);
            if(state == 0){
                shooter.solenoid_extensions.set(true);
                shooter.solenoid_trigger.set(true);
                Timer.delay(1.0);
                shooter.tensionPID.setSetpoint(shooter.uncockTension);
                state += 1;
            }else if(state == 1){
                state += shooter.shooterDown.get() ? 1 : 0;  
            }else if(state == 2){
                shooter.solenoid_trigger.set(false);
                shooter.tensionPID.setSetpoint(shooter.shotPowerHigh);
                shooter.solenoid_collector.set(false);
                shooter.BallPickup.set(-1.0);
                shooter.retractArm();
                state += 1;
            }else if(state == 3){
                state += !shooter.Arm_Out.get() ? 1 : 0;
            }else if(state == 4){
                shooter.extendArm();
                shooter.solenoid_collector.set(true);
                state += 1;
            }else if(state ==5){
                state += shooter.tensionPID.onTarget() ? 1 : 0;
            }else if(state == 6){
                shooter.tensionPID.disable();
                shooter.solenoid_trigger.set(true);
                Timer.delay(1.0);
                state += 1;
            }
        }

        protected boolean isFinished() {
            return state == 6;
        }

        protected void end() {
            shooter.BallPickup.set(0.0);
            drive.stopDrive();
            System.out.println("Auton2 Ended");
        }

        protected void interrupted() {
            shooter.BallPickup.set(0.0);
            drive.stopDrive();
            System.out.println("Auton2 Interrupted");
        }
    
    }
}