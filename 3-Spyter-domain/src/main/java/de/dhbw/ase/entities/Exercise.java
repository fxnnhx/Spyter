package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public class Exercise {

    private final Corrector corrector;
    private final TextProgress textProgress;

    public Exercise(Corrector corrector, TextProgress textProgress) {
        this.corrector = corrector;
        this.textProgress = textProgress;
    }

    public boolean isFinished() {
        return textProgress.isFinished();
    }

    public AdvanceType takeCharacter(SpyterCharacter character) {
        AdvanceType currentAdvance = corrector.AdvanceBehavior(textProgress.getNextCharacter(), character);
        switch (currentAdvance) {
            case HOLD -> {}
            case ADVANCE_CORRECT -> {textProgress.advance(CharacterCorrectionType.CORRECT);}
            case ADVANCE_INCORRECT -> {textProgress.advance(CharacterCorrectionType.INCORRECT);}
            default -> {throw new IllegalStateException("Unexpected value: " + currentAdvance);}
        }
        return currentAdvance;
    }

    public void deleteLastCharacter() {
        textProgress.removeLastChar();
    }

    public ExerciseEvaluator toEvaluator() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
