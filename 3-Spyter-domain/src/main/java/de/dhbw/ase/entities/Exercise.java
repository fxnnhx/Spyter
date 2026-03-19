package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public class Exercise {

    private final Corrector corrector;

    public Exercise(Corrector corrector) {
        this.corrector = corrector;
    }

    public boolean isFinished() {
        return corrector.isFinished();
    }

    public AdvanceType takeCharacter(SpyterCharacter character) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void deleteLastCharacter() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ExerciseEvaluator toEvaluator() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
