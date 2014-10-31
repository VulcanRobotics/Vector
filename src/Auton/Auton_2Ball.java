/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auton;

import commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import subsystems.drive.C_AutoDrive;
/**
 *
 * @author liamcook
 * @author afiolmahon
 */
public class Auton_2Ball extends CommandGroup {
        
    public Auton_2Ball() {
        addSequential(new PreDrive());
        addSequential(new C_AutoDrive(11.5));
        addSequential(new PostDrive());
    }
    
    //state machine commands defined below
    class PreDrive extends CommandBase{
        
        int state = 0;
        
        protected void initialize() {
        }

        protected void execute() {
            System.out.println("Auton2 State[PreDrive]: "+state);
            if(state == 0){
                pickup.armOut();
                shooter.tensionPID.setAbsoluteTolerance(0.1);
                shooter.tensionPID.setSetpoint(shooter.shotPowerHigh);
                shooter.tensionPID.enable();
                state += 1;
            }
            else if(state == 1){
                state += pickup.Arm_Out.get() ? 1 : 0;
            }else if(state == 2){
                Timer.delay(1.0);
                pickup.rollBallIn(0.07f);
                state +=1;
            }
        }

        protected boolean isFinished() {
            return state == 3;
        }

        protected void end() {
            System.out.println("Auton2[PreDrive] Ended");
        }

        protected void interrupted() {
            System.out.println("Auton2[PreDrive] Interrupted");
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
                pickup.collectorDown(); 
                pickup.rollBallIn(1.0f);
                pickup.armIn();
                state += 1;
            }else if(state == 3){
                Timer.delay(1);
                state += 1;
            }else if(state == 4){
                pickup.armOut();
                state += 1;
            }else if(state ==5){
                state += shooter.tensionPID.onTarget() && pickup.hasBall()? 1 : 0;
            }else if(state == 6){
                pickup.collectorUp();
                Timer.delay(0.5);
                shooter.tensionPID.disable();
                shooter.solenoid_trigger.set(true);
                Timer.delay(1.0);
                state += 1;
            }
        }

        protected boolean isFinished() {
            return state == 7 | !DriverStation.getInstance().isAutonomous();
        }

        protected void end() {
            pickup.stopRollers();
            drive.stopDrive();
            System.out.println("Auton2[PostDrive] Ended");
        }

        protected void interrupted() {
            pickup.stopRollers();
            drive.stopDrive();
            System.out.println("Auton2[PostDrive] Interrupted");
        }
    
    }
}