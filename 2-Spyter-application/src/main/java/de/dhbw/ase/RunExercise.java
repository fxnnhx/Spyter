package de.dhbw.ase;

import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.ExerciseEvaluator;
import de.dhbw.ase.valueObjects.SpyterCharacter;

import java.util.Optional;

public class RunExercise {
    Exercise exercise;

    public RunExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Optional<ExerciseEvaluator> run(UIHandle ui) {
        boolean interrupted = false;
        while ( !(exercise.isFinished() || interrupted) ) {
            switch (ui.getNextAction()){
                case Action.Exit _ -> interrupted = true;
                case Action.RemovedChar _ -> actOnRemovedChar(ui);
                case Action.TypedChar typedChar -> actOnTypedChar(ui, typedChar.getCharacter());
            }
        }

        if (interrupted) {
            return Optional.empty();
        }else {
            return Optional.of(this.exercise.toEvaluator());
        }
    }

    private void actOnTypedChar(UIHandle ui, SpyterCharacter character) {
        switch (exercise.takeCharacter(character)) {
            case HOLD -> ui.hold(character);
            case ADVANCE_CORRECT -> ui.typeCorrectCharacter(character);
            case ADVANCE_INCORRECT -> ui.typeIncorrectCharacter(character);
        }
    }

    private void actOnRemovedChar(UIHandle ui) {
        ui.removeChar();
    }
}
