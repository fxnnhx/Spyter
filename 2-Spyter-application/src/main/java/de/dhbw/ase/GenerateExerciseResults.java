package de.dhbw.ase;

import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.ExerciseEvaluator;
import de.dhbw.ase.valueObjects.ExerciseResult;

public class GenerateExerciseResults {

    public ExerciseResult getResult(ExerciseEvaluator evaluator) {
        return evaluator.generateExerciseResult();
    }
}
