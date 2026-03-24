package de.dhbw.ase;

import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.ExerciseEvaluator;
import de.dhbw.ase.valueObjects.SpyterCharacter;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class RunExercise {
    Exercise exercise;

    public RunExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Optional<ExerciseEvaluator> run(RunningExerciseUI ui) {
        boolean interrupted = false;

        ui.showText(exercise.getText());

        Instant start = Instant.now();
        while ( !(exercise.isFinished() || interrupted) ) {
            switch (ui.getNextAction()){
                case Action.Exit _ -> interrupted = true;
                case Action.RemovedChar _ -> actOnRemovedChar(ui);
                case Action.TypedChar typedChar -> actOnTypedChar(ui, typedChar.getCharacter());
            }
        }

        Instant end = Instant.now();

        if (interrupted) {
            return Optional.empty();
        }else {
            return Optional.of(this.exercise.toEvaluator(Duration.between(start, end)));
        }
    }

    private void actOnTypedChar(RunningExerciseUI ui, SpyterCharacter character) {
        switch (exercise.takeCharacter(character)) {
            case HOLD -> ui.hold_incorrect(character);
            case ADVANCE_CORRECT -> ui.appendCorrectCharacter(character);
            case ADVANCE_INCORRECT -> ui.appendIncorrectCharacter(character);
        }
    }

    private void actOnRemovedChar(RunningExerciseUI ui) {
        ui.removeChar();
    }
}
