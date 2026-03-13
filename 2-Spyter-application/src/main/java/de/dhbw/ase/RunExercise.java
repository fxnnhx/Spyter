package de.dhbw.ase;

import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.ExerciseEvaluator;
import de.dhbw.ase.entities.TextProgress;

import java.util.Optional;

public class RunExercise {
    TextProgress exercise;

    public RunExercise(TextProgress exercise) {
        this.exercise = exercise;
    }

    public Optional<ExerciseEvaluator> run(UIHandle ui) {
        while (!exercise.isFinished()) {

        }

        return Optional.empty();
        //return this.exercise.toEvaluator();
    }
}
