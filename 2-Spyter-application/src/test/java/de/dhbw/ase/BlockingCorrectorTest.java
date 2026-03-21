package de.dhbw.ase;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.MistakeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockingCorrectorTest {

    @Test
    void advanceBehavior_Hold() {
        SpyterCharacter expectedCharacter = getSpyterCharacter('a');
        SpyterCharacter actualCharacter = getSpyterCharacter('b');
        assert (new BlockingCorrector().AdvanceBehavior(expectedCharacter, actualCharacter) == AdvanceType.HOLD);
    }

    @Test
    void advanceBehavior_Correct() {
        SpyterCharacter expectedCharacter = getSpyterCharacter('a');
        SpyterCharacter actualCharacter = getSpyterCharacter('a');
        assert (new BlockingCorrector().AdvanceBehavior(expectedCharacter, actualCharacter) == AdvanceType.ADVANCE_CORRECT);
    }

    @Test
    void getMistakeCountAfterCreation() {
        BlockingCorrector blockingCorrector = new BlockingCorrector();
        assert blockingCorrector.mistakeCount.equals(new MistakeCount());
    }

    @Test
    void getMistakeCountAfterCorrectAdvance() {
        BlockingCorrector blockingCorrector = new BlockingCorrector();
        blockingCorrector.AdvanceBehavior(getSpyterCharacter('a'), getSpyterCharacter('a'));
        assert blockingCorrector.mistakeCount.equals(new MistakeCount());
    }

    @Test
    void getMistakeCountAfterIncorrectAdvance() {
        BlockingCorrector blockingCorrector = new BlockingCorrector();
        blockingCorrector.AdvanceBehavior(getSpyterCharacter('a'), getSpyterCharacter('b'));
        MistakeCount mistakeCount = new MistakeCount();
        mistakeCount.addMistake();
        assert blockingCorrector.mistakeCount.equals(mistakeCount);
    }

    private SpyterCharacter getSpyterCharacter(char c) {
        return SpyterCharacter.tryFrom(new MockCharacterDomain(), c).get();
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