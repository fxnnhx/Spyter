package de.dhbw.ase;

import de.dhbw.ase.entities.ExerciseEvaluator;
import de.dhbw.ase.entities.TextProgress;
import de.dhbw.ase.valueObjects.SpyterCharacter;

import java.util.Optional;

public class RunExercise {
    TextProgress exercise;

    public RunExercise(TextProgress exercise) {
        this.exercise = exercise;
    }

    public Optional<ExerciseEvaluator> run(UIHandle ui) {
        while (!exercise.isFinished()) {
            switch (ui.getNextAction()){
                case Action.Exit () -> handleExit(ui);
                case Action.RemovedChar () -> handleRemovedChar(ui);
                case Action.TypedChar typedChar -> handleTypedChar(ui, typedChar.getCharacter());
            }
        }

        return Optional.empty();
        //return this.exercise.toEvaluator();
    }

    private void handleTypedChar(UIHandle ui, SpyterCharacter character) {
    }

    private void handleRemovedChar(UIHandle ui) {
    }

    private void handleExit(UIHandle ui) {

    }
}
