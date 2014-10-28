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
import subsystems.shooter.C_Shoot;
/**
 *
 * @author liamcook
 * @author afiolmahon
 */
public class Auton_2Ball extends CommandGroup {
        
<<<<<<< HEAD:src/Auton/CG_Auton2.java
    public CG_Auton2() {
        addParallel(new C_FindShortTension);
=======
    public Auton_2Ball() {
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f:src/Auton/Auton_2Ball.java
        addSequential(new C_ExtendArm());
        addSequential(new C_PickupBallToBumper());
        
        addSequential(new C_AutoDrive(11.5));
        
        addSequential(new C_ExtendArm());
        addSequential(new C_LoadBallOnBumper());
        addSequential(new C_ExtendArm()); //just to be sure
        addSequential(new C_Shoot());
        //reload
        addSequential(new C_PickupBall());
        //Need to add reload command into this sequence
        addSequential(new C_ExtendArm()); //just to be sure
        addSequential(new C_Shoot());
        
    }
    
    //state machine commands defined below
    class PreDrive extends CommandBase{
        
        int state = 0;
        
        protected void initialize() {
        }

        protected void execute() {
            System.out.println("Auton2 State[PreDrive]: "+state);
            if(state == 0){
<<<<<<< HEAD:src/Auton/CG_Auton2.java
               // shooter.extendArm();
=======
                pickup.armOut();
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f:src/Auton/Auton_2Ball.java
                shooter.tensionPID.setAbsoluteTolerance(0.1);
                shooter.tensionPID.setSetpoint(shooter.shotPowerHigh);
                shooter.tensionPID.enable();
                state += 1;
            }
            else if(state == 1){
<<<<<<< HEAD:src/Auton/CG_Auton2.java
               // state += shooter.Arm_Out.get() ? 1 : 0;
            }else if(state == 2){
                Timer.delay(1.0);
               // shooter.BallPickup.set(-0.07);
=======
                state += pickup.Arm_Out.get() ? 1 : 0;
            }else if(state == 2){
                Timer.delay(1.0);
                pickup.rollBallIn(0.07f);
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f:src/Auton/Auton_2Ball.java
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
                //shooter.solenoid_extensions.set(true);
                //shooter.solenoid_trigger.set(true);
                Timer.delay(1.0);
                //shooter.tensionPID.setSetpoint(shooter.uncockTension);
                state += 1;
            }else if(state == 1){
                //state += shooter.shooterDown.get() ? 1 : 0;  
            }else if(state == 2){
<<<<<<< HEAD:src/Auton/CG_Auton2.java
                //shooter.solenoid_trigger.set(false);
                //shooter.tensionPID.setSetpoint(shooter.shotPowerHigh);
                //shooter.solenoid_collector.set(false);
                //shooter.BallPickup.set(-1.0);
               // shooter.retractArm();
                state += 1;
            }else if(state == 3){
               // state += !shooter.Arm_Out.get() ? 1 : 0;
            }else if(state == 4){
                //shooter.extendArm();
                ////shooter.solenoid_collector.set(true);
=======
                shooter.solenoid_trigger.set(false);
                shooter.tensionPID.setSetpoint(shooter.shotPowerHigh);
                pickup.collectorDown();
                pickup.rollBallIn(1.0f);
                pickup.armIn();
                state += 1;
            }else if(state == 3){
                state += !pickup.Arm_Out.get() ? 1 : 0;
            }else if(state == 4){
                pickup.armOut();
                pickup.collectorUp();
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f:src/Auton/Auton_2Ball.java
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
            return state == 7;
        }

        protected void end() {
<<<<<<< HEAD:src/Auton/CG_Auton2.java
            //shooter.BallPickup.set(0.0);
=======
            pickup.stopRollers();
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f:src/Auton/Auton_2Ball.java
            drive.stopDrive();
            System.out.println("Auton2[PostDrive] Ended");
        }

        protected void interrupted() {
<<<<<<< HEAD:src/Auton/CG_Auton2.java
            //shooter.BallPickup.set(0.0);
=======
            pickup.stopRollers();
>>>>>>> 1f2a186eade3f38e4977cc716a5f6c82d8a2a88f:src/Auton/Auton_2Ball.java
            drive.stopDrive();
            System.out.println("Auton2[PostDrive] Interrupted");
        }
    
    }
}