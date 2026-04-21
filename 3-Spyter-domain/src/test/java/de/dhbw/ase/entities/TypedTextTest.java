package de.dhbw.ase.entities;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.helpers.FakeCharacterDomain;
import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypedTextTest {

    @Test
    void getCorrectCharacterCount() {
        SpyterText text = new SpyterText(new FakeCharacterDomain(), "Hello");
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
        SpyterText text = new SpyterText(new FakeCharacterDomain(), "Hello world");
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
        assertEquals(2, typedText.getWordCount());
    }

    @Test
    void text() {
        SpyterText text = new SpyterText(new FakeCharacterDomain(), "Sample text");
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
        SpyterText text = new SpyterText(new FakeCharacterDomain(), "Typing");
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