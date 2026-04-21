package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.*;

import java.time.Duration;

public class Exercise {

    private final Corrector corrector;
    private final TextProgress textProgress;

    protected Exercise(Corrector corrector, TextProgress textProgress) {
        this.corrector = corrector;
        this.textProgress = textProgress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public SpyterText getText(){
        return this.textProgress.getText();
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

    public ExerciseEvaluator toEvaluator(Duration duration) {
        return new ExerciseEvaluator(textProgress.getTypedText(), corrector.getMistakeCount(), duration);
    }

    public static class Builder {
        private Corrector corrector;
        private SpyterText text;
        private TextGenerator generator;

        public void setCorrector(Corrector corrector) {
            this.corrector = corrector;
        }

        public void setGenerator(TextGenerator generator) {
            this.generator = generator;
        }

        public void setText(SpyterText text) {
            this.text = text;
        }

        public Exercise build() {
            if (corrector == null) {
                throw new IllegalStateException("Corrector must be set");
            }
            if (text == null) {
                throw new IllegalStateException("Text must be set");
            }
            SpyterText text = (generator == null) ? this.text : generator.generate(this.text);
            return new Exercise(corrector, new TextProgress(text));
        }
    }

}
