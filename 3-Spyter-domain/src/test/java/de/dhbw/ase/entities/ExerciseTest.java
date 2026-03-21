package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ExerciseTest {
    @Test
    void isFinished_shouldReturnFalse() {
        Exercise exercise = new Exercise(new EmptyCorrector(), new FakeFinishedTextProgress(false));
        assert !exercise.isFinished();
    }

    @Test
    void isFinished_shouldReturnTrue() {
        Exercise exercise = new Exercise(new EmptyCorrector(), new FakeFinishedTextProgress(true));
        assert exercise.isFinished();
    }

    @Test
    void takeCharacter_Hold(){
        FakeTextProgress textProgress = new FakeTextProgress();
        Exercise exercise = new Exercise(new FakeAdvanceTypeCorrector(AdvanceType.HOLD), textProgress);
        AdvanceType actualExerciseAdvanceType = exercise.takeCharacter(null);
        assert actualExerciseAdvanceType == AdvanceType.HOLD;
        assert textProgress.getLastAdvance() == null;
    }

    @Test
    void takeCharacter_AdvanceCorrect(){
        FakeTextProgress textProgress = new FakeTextProgress();
        Exercise exercise = new Exercise(new FakeAdvanceTypeCorrector(AdvanceType.ADVANCE_CORRECT), textProgress);
        AdvanceType actualExerciseAdvanceType = exercise.takeCharacter(null);
        assert actualExerciseAdvanceType == AdvanceType.ADVANCE_CORRECT;
        assert textProgress.getLastAdvance() == CharacterCorrectionType.CORRECT;
    }

    @Test
    void takeCharacter_AdvanceIncorrect(){
        FakeTextProgress textProgress = new FakeTextProgress();
        Exercise exercise = new Exercise(new FakeAdvanceTypeCorrector(AdvanceType.ADVANCE_INCORRECT), textProgress);
        AdvanceType actualExerciseAdvanceType = exercise.takeCharacter(null);
        assert actualExerciseAdvanceType == AdvanceType.ADVANCE_INCORRECT;
        assert textProgress.getLastAdvance() == CharacterCorrectionType.INCORRECT;
    }

    @Test
    void removeCharacter(){
        FakeTextProgress textProgress = new FakeTextProgress();
        Exercise exercise = new Exercise(null, textProgress);
        exercise.deleteLastCharacter();
        assert textProgress.charsRemoved() == 1;
    }


    class FakeFinishedTextProgress extends TextProgress {
        boolean isFinished;

        FakeFinishedTextProgress(boolean isFinished) {
            super(new SpyterText(new ArrayList<>()));
            this.isFinished = isFinished;
        }

        @Override
        public boolean isFinished() {
            return this.isFinished;
        }
    }

    class  EmptyCorrector implements Corrector {

        @Override
        public AdvanceType AdvanceBehavior(SpyterCharacter expected, SpyterCharacter actual) {
            throw new UnsupportedOperationException("Not implemented.");
        }

        @Override
        public MistakeCount getMistakeCount() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    class FakeAdvanceTypeCorrector implements Corrector {

        AdvanceType advanceType;

        FakeAdvanceTypeCorrector(AdvanceType advanceType) {
            this.advanceType = advanceType;
        }

        @Override
        public AdvanceType AdvanceBehavior(SpyterCharacter expected, SpyterCharacter actual) {
            return this.advanceType;
        }

        @Override
        public MistakeCount getMistakeCount() {
            return null;
        }
    }

    class FakeTextProgress extends TextProgress {

        CharacterCorrectionType lastAdvancedCorrectionType;
        int charsRemoved = 0;

        public FakeTextProgress() {
            super(new SpyterText(new ArrayList<>()));
        }

        @Override
        public void advance(CharacterCorrectionType correctionType) {
            this.lastAdvancedCorrectionType = correctionType;
        }

        public CharacterCorrectionType getLastAdvance() {
            return this.lastAdvancedCorrectionType;
        }

        @Override
        public SpyterCharacter getNextCharacter() {
            return null;
        }

        @Override
        public boolean isNextChar(SpyterCharacter character) {
            return false;
        }

        @Override
        public void removeLastChar() {
            this.charsRemoved++;
        }

        public int charsRemoved() {
            return this.charsRemoved;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public TypedText getTypedText() {
            return null;
        }
    }

}