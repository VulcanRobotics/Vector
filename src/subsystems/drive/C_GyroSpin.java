package subsystems.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author afiolmahon
 */
public class C_GyroSpin extends CommandBase {
    
    //PID Values
        double OutputRangeTop = 0.7;
        double OutputRangeBottom = -0.7;
        double Kp = 3.000;
        double Ki = 0.000;
        double Kd = 0.000;
    //Control Objects
        O_DrivePIDOutput PIDDrive = new O_DrivePIDOutput();
        PIDController GyroPid = new PIDController(Kp, Ki, Kd, drive.driveGyro, PIDDrive);
    //Control Values
        double desiredAngle;
        double TargetAngleAllowance = 3;
        double TurnSpeedAllowance = 0.1;
        
    public C_GyroSpin(double desiredAngle) {
        requires(drive);
        this.desiredAngle = desiredAngle;
        GyroPid.setOutputRange(OutputRangeBottom, OutputRangeTop);
    }

    protected void initialize() {
        GyroPid.setSetpoint(desiredAngle);
        GyroPid.enable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !oi.Button_AutoGyroDrive.get();
    }

    protected void end() {
        GyroPid.disable();
    }

    protected void interrupted() {
        GyroPid.disable();
    }
    
    class O_DrivePIDOutput implements PIDOutput{

    public void pidWrite(double output) {
        CommandBase.drive.chassis.arcadeDrive(-output, 0);
    }
    
}
}
