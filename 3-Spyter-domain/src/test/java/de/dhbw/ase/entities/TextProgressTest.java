package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class TextProgressTest {

    void assertedAdvanceWithInputCheck(TextProgress progress, char character, CharacterCorrectionType correctionType) {
        assertTrue(progress.isNextChar(assertedCharConversion(character)));
        progress.advance(correctionType);
    }

    SpyterCharacter assertedCharConversion(char character) {
        return SpyterCharacter.tryFrom(character).orElseThrow();
    }

    @Test
    void advance_shouldIncrementProgressIndex() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        assertFalse(progress.isFinished());
        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        assertFalse(progress.isFinished());
        assertedAdvanceWithInputCheck(progress, 'b', CharacterCorrectionType.CORRECT);
        assertFalse(progress.isFinished());
        assertedAdvanceWithInputCheck(progress, 'c', CharacterCorrectionType.CORRECT);
        assertTrue(progress.isFinished());
    }

    @Test
    void advance_shouldRecordCorrectionType() {
        SpyterText text = new SpyterText("ab");
        TextProgress progress = new TextProgress(text);
        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        assertedAdvanceWithInputCheck(progress, 'b', CharacterCorrectionType.CORRECT);

        TypedText typedText = progress.getTypedText();
        assertEquals(2, typedText.characterCorrections().size());
        assertEquals(CharacterCorrectionType.CORRECT, typedText.characterCorrections().get(0));
        assertEquals(CharacterCorrectionType.INCORRECT, typedText.characterCorrections().get(1));
    }

    @Test
    void isNextChar_shouldReturnTrueForMatchingCharacter() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        assertTrue(progress.isNextChar(assertedCharConversion('a')));
        assertFalse(progress.isNextChar(assertedCharConversion('b')));

        progress.advance(CharacterCorrectionType.CORRECT);
        assertTrue(progress.isNextChar(assertedCharConversion('b')));
        assertFalse(progress.isNextChar(assertedCharConversion('a')));
    }

    @Test
    void isNextChar_shouldReturnCorrectCharacterAfterAdvance() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        assertTrue(progress.isNextChar(assertedCharConversion('b')));

        assertedAdvanceWithInputCheck(progress, 'b', CharacterCorrectionType.CORRECT);
        assertTrue(progress.isNextChar(assertedCharConversion('c')));
    }

    @Test
    void removeLastChar_shouldDecrementProgressIndex() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        assertTrue(progress.isNextChar(assertedCharConversion('b')));

        progress.removeLastChar();
        assertTrue(progress.isNextChar(assertedCharConversion('a')));
    }

    @Test
    void removeLastChar_shouldRemoveCorrectionFromList() {
        SpyterText text = new SpyterText("ab");
        TextProgress progress = new TextProgress(text);

        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        progress.advance(CharacterCorrectionType.INCORRECT);

        assertEquals(2, progress.getTypedText().characterCorrections().size());

        progress.removeLastChar();
        assertEquals(1, progress.getTypedText().characterCorrections().size());
        assertEquals(CharacterCorrectionType.CORRECT, progress.getTypedText().characterCorrections().get(0));
    }

    @Test
    void removeLastChar_shouldDoNothingAtBeginning() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        progress.removeLastChar();
        assertTrue(progress.isNextChar(assertedCharConversion('a')));
        assertEquals(0, progress.getTypedText().characterCorrections().size());
    }

    @Test
    void isFinished_shouldReturnFalseForNewProgress() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        assertFalse(progress.isFinished());
    }

    @Test
    void isFinished_shouldReturnTrueWhenAllCharactersTyped() {
        SpyterText text = new SpyterText("ab");
        TextProgress progress = new TextProgress(text);

        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        assertFalse(progress.isFinished());

        assertedAdvanceWithInputCheck(progress, 'b', CharacterCorrectionType.CORRECT);
        assertTrue(progress.isFinished());
    }

    @Test
    void isFinished_shouldReturnFalseAfterRemoveLastChar() {
        SpyterText text = new SpyterText("a");
        TextProgress progress = new TextProgress(text);

        assertedAdvanceWithInputCheck(progress, 'a', CharacterCorrectionType.CORRECT);
        assertTrue(progress.isFinished());

        progress.removeLastChar();
        assertFalse(progress.isFinished());
    }

    @Test
    void getTypedText_shouldReturnCorrectText() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        progress.advance(CharacterCorrectionType.CORRECT);
        progress.advance(CharacterCorrectionType.INCORRECT);

        TypedText typedText = progress.getTypedText();
        assertEquals(text, typedText.text());
        assertEquals(2, typedText.characterCorrections().size());
    }

    @Test
    void getTypedText_shouldReturnEmptyCorrectionsForNewProgress() {
        SpyterText text = new SpyterText("abc");
        TextProgress progress = new TextProgress(text);

        TypedText typedText = progress.getTypedText();
        assertEquals(text, typedText.text());
        assertTrue(typedText.characterCorrections().isEmpty());
    }
}