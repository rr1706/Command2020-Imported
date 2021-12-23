package frc.robot.Commands.Autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.FeedShooter;
import frc.robot.Commands.FloorIntake;
import frc.robot.Commands.GoalShoot;
import frc.robot.Constants.AutoConstants;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.Turret;
import frc.robot.Subsystems.Swerve.Drivetrain;
import frc.robot.Utilities.AutoFromPathPlanner;

public class AutoRight extends ParallelCommandGroup {

    public AutoRight(Drivetrain drive, Intake intake, Shooter shooter, Turret turret){
        addCommands( new SequentialCommandGroup(

        AutoFromPathPlanner.autoCommand(drive,"InitialBackup",AutoConstants.kMaxSpeed),

        new FeedShooter(shooter, turret, intake).withTimeout(3.00),

        AutoFromPathPlanner.autoCommand(drive, "IntakeBalls",2.50).raceWith(
            new FloorIntake(intake)),
            
        AutoFromPathPlanner.autoCommand(drive, "DriveToShoot",AutoConstants.kMaxSpeed),

        new FeedShooter(shooter, turret, intake).withTimeout(5.00),
        
        AutoFromPathPlanner.autoCommand(drive, "FinalMove",AutoConstants.kMaxSpeed)),

        new GoalShoot(shooter, turret, drive));
    }
    
}
