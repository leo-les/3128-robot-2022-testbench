package frc.team3128.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team3128.subsystems.TravClimber;

public class Climb extends SequentialCommandGroup{

    public Climb(TravClimber m_climber){
        addCommands(
            new InstantCommand(() -> m_climber.climberExtend()),
            new InstantCommand(() -> m_climber.angleArm()),
            new InstantCommand(() -> m_climber.climberExtend()),
            new InstantCommand(() -> m_climber.climberRetract()),
            new InstantCommand(() -> m_climber.unAngleArm()),
            new InstantCommand(() -> m_climber.climberRetract())


        );
    }

}