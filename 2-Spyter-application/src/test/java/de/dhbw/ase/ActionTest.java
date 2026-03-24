package de.dhbw.ase;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    void getCharacterThrowsForExit() {
        Action action = new Action.Exit();
        assertThrows(UnsupportedOperationException.class, action::getCharacter);
    }

    @Test
    void getCharacterThrowsForRemoveChar() {
        Action action = new Action.RemovedChar();
        assertThrows(UnsupportedOperationException.class, action::getCharacter);
    }

    @Test
    void getCharacterForCharacterTyped() {
        SpyterCharacter expectedChar = SpyterCharacter.tryFrom(new CharactorDomainFake(), 'a').get();
        Action.TypedChar action = new Action.TypedChar(expectedChar);
        assert(action.getCharacter() == expectedChar);
    }

    class CharactorDomainFake implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            return false;
        }

        @Override
        public boolean isDomainCharacter(char character) {
            return true;
        }

        @Override
        public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            return null;
        }
    }

}