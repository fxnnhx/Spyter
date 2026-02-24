package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.MistakeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public interface Corrector {
    AdvanceType take(SpyterCharacter character);

    void deleteLastCharacter();

    boolean isFinished();

    ExerciseEvaluator toExerciseEvaluator();

    MistakeCount getMistakeCount();

    TextProgress getTextProgress();
}
