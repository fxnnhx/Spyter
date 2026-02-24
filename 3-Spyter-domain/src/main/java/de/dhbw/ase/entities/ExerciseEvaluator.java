package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.ExerciseResult;
import de.dhbw.ase.valueObjects.MistakeCount;

import java.time.Duration;

public class ExerciseEvaluator {
    private final TypedText typedText;
    private final MistakeCount mistakeCount;
    private final Duration duration;

    public ExerciseEvaluator(TypedText typedText, MistakeCount mistakeCount, Duration duration) {
        this.typedText = typedText;
        this.mistakeCount = mistakeCount;
        this.duration = duration;
    }

    public ExerciseResult generateExerciseResult() {
        return new ExerciseResult(wordsPerSecond(), charactersPerSecond(), (int) duration.getSeconds(), mistakeCount.getValue());
    }

    private double charactersPerSecond() {
        long correctCharacters = typedText.getCorrectCharacterCount();
        return (double) correctCharacters / this.duration.getSeconds();
    }

    private double wordsPerSecond() {
        long correctWords = typedText.getCorrectWordCount();
        return (double) correctWords / this.duration.getSeconds();
    }
}
