package de.dhbw.ase.entities;

import de.dhbw.ase.constants.CharacterDomain;
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
        SpyterText text = new SpyterText(new MockCharacterDomain(), "Hello");
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
    void getWordCount() {
        SpyterText text = new SpyterText(new MockCharacterDomain(), "Hello world");
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
        SpyterText text = new SpyterText(new MockCharacterDomain(), "Sample text");
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
        SpyterText text = new SpyterText(new MockCharacterDomain(), "Typing");
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
    class MockCharacterDomain implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            return character == ' ';
        }

        @Override
        public boolean isDomainCharacter(char character) {
            return true;
        }

        @Override
        public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}