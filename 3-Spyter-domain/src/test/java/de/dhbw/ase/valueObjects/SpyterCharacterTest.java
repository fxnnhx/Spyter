package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpyterCharacterTest {

    @Test
    void getValue() {
        char c = 'a';
        SpyterCharacter spyterCharacter = SpyterCharacter.tryFrom(new MockCharacterDomain(c), c).get();
        assert(c == spyterCharacter.getValue());
    }
}

class MockCharacterDomain implements CharacterDomain {
    char character;

    public MockCharacterDomain(char allowedCharacter) {
        this.character = allowedCharacter;
    }

    @Override
    public boolean isDelimiter(char character) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean isDomainCharacter(char character) {
        return character == this.character;
    }

    @Override
    public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
        throw new UnsupportedOperationException("Not implemented");
    }
}