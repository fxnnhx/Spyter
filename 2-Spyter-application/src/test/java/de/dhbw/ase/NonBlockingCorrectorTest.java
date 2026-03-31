package de.dhbw.ase;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.MistakeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import org.junit.jupiter.api.Test;


class NonBlockingCorrectorTest {

    @Test
    void advanceBehavior_Hold() {
        SpyterCharacter expectedCharacter = getSpyterCharacter('a');
        SpyterCharacter actualCharacter = getSpyterCharacter('b');
        assert (new NonBlockingCorrector().AdvanceBehavior(expectedCharacter, actualCharacter) == AdvanceType.ADVANCE_INCORRECT);
    }

    @Test
    void advanceBehavior_Correct() {
        SpyterCharacter expectedCharacter = getSpyterCharacter('a');
        SpyterCharacter actualCharacter = getSpyterCharacter('a');
        assert (new NonBlockingCorrector().AdvanceBehavior(expectedCharacter, actualCharacter) == AdvanceType.ADVANCE_CORRECT);
    }

    @Test
    void getMistakeCountAfterCreation() {
        NonBlockingCorrector corrector = new NonBlockingCorrector();
        assert corrector.mistakeCount.equals(new MistakeCount());
    }

    @Test
    void getMistakeCountAfterCorrectAdvance() {
        NonBlockingCorrector corrector = new NonBlockingCorrector();
        corrector.AdvanceBehavior(getSpyterCharacter('a'), getSpyterCharacter('a'));
        assert corrector.mistakeCount.equals(new MistakeCount());
    }

    @Test
    void getMistakeCountAfterIncorrectAdvance() {
        NonBlockingCorrector corrector = new NonBlockingCorrector();
        corrector.AdvanceBehavior(getSpyterCharacter('a'), getSpyterCharacter('b'));
        MistakeCount mistakeCount = new MistakeCount();
        mistakeCount.addMistake();
        assert corrector.mistakeCount.equals(mistakeCount);
    }

    private SpyterCharacter getSpyterCharacter(char c) {
        return SpyterCharacter.tryFrom(new NonBlockingCorrectorTest.MockCharacterDomain(), c).get();
    }

    class MockCharacterDomain implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public boolean isDomainCharacter(char character) {
            return true;
        }

        @Override
        public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}