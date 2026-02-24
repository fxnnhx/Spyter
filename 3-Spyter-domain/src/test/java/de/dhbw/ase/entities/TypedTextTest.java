package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterText;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypedTextTest {

    @Test
    void getCorrectCharacterCount() {
        SpyterText text = new SpyterText("Hello");
        List<CharacterCorrectionType> corrections = List.of(
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.INCORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT
        );
        TypedText typedText = new TypedText(text, corrections);
        assertEquals(4, typedText.getCorrectCharacterCount());
    }

    @Test
    void getCorrectWordCount() {
        SpyterText text = new SpyterText("Hello world");
        List<CharacterCorrectionType> corrections = List.of(
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.INCORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT
        );
        TypedText typedText = new TypedText(text, corrections);
        assertEquals(1, typedText.getCorrectWordCount());
    }

    @Test
    void text() {
        SpyterText text = new SpyterText("Sample text");
        List<CharacterCorrectionType> corrections = List.of(
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.INCORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT
        );
        TypedText typedText = new TypedText(text, corrections);
        assertEquals(text, typedText.text());
    }

    @Test
    void characterCorrections() {
        SpyterText text = new SpyterText("Typing");
        List<CharacterCorrectionType> corrections = List.of(
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.INCORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.CORRECT,
                CharacterCorrectionType.INCORRECT,
                CharacterCorrectionType.CORRECT
        );
        TypedText typedText = new TypedText(text, corrections);
        assertEquals(corrections, typedText.characterCorrections());
    }
}