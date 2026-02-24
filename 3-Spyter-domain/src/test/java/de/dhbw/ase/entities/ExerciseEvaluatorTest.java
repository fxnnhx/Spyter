package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.ExerciseResult;
import de.dhbw.ase.valueObjects.MistakeCount;
import de.dhbw.ase.valueObjects.SpyterText;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseEvaluatorTest {

    TypedText mockTypedText(String text, List<CharacterCorrectionType> correctionTypes) {
        assert text.length() == correctionTypes.size();
        SpyterText spyterText = new SpyterText(text);
        return new TypedText(spyterText, correctionTypes);
    }

    List<CharacterCorrectionType> mockUniformCorrectionList(int size, CharacterCorrectionType defaultValue) {
        return new ArrayList<>(Collections.nCopies(size, defaultValue));
    }

    List<CharacterCorrectionType> mockSplitCorrectionList(int size, int splitIndex, CharacterCorrectionType valueBeforeSplit, CharacterCorrectionType valueAfterSplit) {
        assert size > splitIndex;
        List<CharacterCorrectionType> list = new ArrayList<>(size);
        list.addAll(mockUniformCorrectionList(splitIndex, valueBeforeSplit));
        list.addAll(mockUniformCorrectionList(size - splitIndex, valueAfterSplit));
        return list;
    }

    MistakeCount mockMistakeCount(int mistakes) {
        MistakeCount mistakeCount = new MistakeCount();
        for (int i = 0; i < mistakes; i++) {
            mistakeCount.addMistake();
        }
        return mistakeCount;
    }

    @Test
    void generateExerciseResult_onlyCorrectRun() {
        String text = "Hello world";
        TypedText typedText = mockTypedText(text, mockUniformCorrectionList(text.length(), CharacterCorrectionType.CORRECT));

        ExerciseEvaluator evaluator = new ExerciseEvaluator(typedText, mockMistakeCount(0), Duration.ofSeconds(10));
        ExerciseResult result = evaluator.generateExerciseResult();

        assertEquals((double) 2 / 10, result.wordsPerSecond());
        assertEquals(0, result.mistakes());
        assertEquals((double) text.length() / 10, result.charactersPerSecond());
    }

    @Test
    void generateExerciseResult_onlyIncorrectRun() {
        String text = "Hello world";
        TypedText typedText = mockTypedText(text, mockUniformCorrectionList(text.length(), CharacterCorrectionType.INCORRECT));

        ExerciseEvaluator evaluator = new ExerciseEvaluator(typedText, mockMistakeCount(text.length()), Duration.ofSeconds(10));
        ExerciseResult result = evaluator.generateExerciseResult();

        assertEquals(0.0, result.wordsPerSecond());
        assertEquals(text.length(), result.mistakes());
        assertEquals(0.0, result.charactersPerSecond());
    }

    @Test
    void generateExerciseResult_mixedRun() {
        String text = "Hello world";
        int uncorrectedMistakes = 3;
        int correctedMistakes = 6;
        int mistakes = uncorrectedMistakes + correctedMistakes;
        TypedText typedText = mockTypedText(text, mockSplitCorrectionList(text.length(), uncorrectedMistakes, CharacterCorrectionType.INCORRECT, CharacterCorrectionType.CORRECT));

        ExerciseEvaluator evaluator = new ExerciseEvaluator(typedText, mockMistakeCount(mistakes), Duration.ofSeconds(10));
        ExerciseResult result = evaluator.generateExerciseResult();

        assertEquals((double)  1 / 10, result.wordsPerSecond());
        assertEquals(mistakes, result.mistakes());
        assertEquals((double) (text.length() - uncorrectedMistakes) / 10, result.charactersPerSecond());
    }

    @Test
    void generateExerciseResult_BigNumbers() {
        String text = "dfhaodfh osadjfhsahj asdhfjkashdf sjdfha kshdf asdhfkjsahdfkh hsfjkh kashdfk haskdjfh jkahsdfjkhskdfhksdhf kashdfk hasdkfjh kajsdhf kasdhf kjshdfkjsahdfkashdfksahfkjsahdfkjhasdkjfh kasdhfkjash kfjdhksjdhflkj sahdlfkj haskljfdh laksjdhfl ajshfdlkjh alsdkjfh klasjhdf klajhsdflkjahsfkj hasdkjfh";
        int wordCount = text.split("\\s+").length;
        int uncorrectedMistakes = 3;
        int correctedMistakes = 6;
        int mistakes = uncorrectedMistakes + correctedMistakes;
        TypedText typedText = mockTypedText(text, mockSplitCorrectionList(text.length(), uncorrectedMistakes, CharacterCorrectionType.INCORRECT, CharacterCorrectionType.CORRECT));

        ExerciseEvaluator evaluator = new ExerciseEvaluator(typedText, mockMistakeCount(mistakes), Duration.ofSeconds(10));
        ExerciseResult result = evaluator.generateExerciseResult();

        assertEquals((double)  (wordCount - 1) / 10, result.wordsPerSecond());
        assertEquals(mistakes, result.mistakes());
        assertEquals((double) (text.length() - uncorrectedMistakes) / 10, result.charactersPerSecond());
    }
}