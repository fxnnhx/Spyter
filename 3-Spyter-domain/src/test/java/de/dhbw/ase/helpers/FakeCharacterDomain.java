package de.dhbw.ase.helpers;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

import java.util.List;

public class FakeCharacterDomain implements CharacterDomain {

    boolean acceptsAllCharacters;
    List<Character> allowedCharacters;

    public FakeCharacterDomain() {
        this.acceptsAllCharacters = true;
    }

    public FakeCharacterDomain(List<Character> allowedCharacters) {
        this.allowedCharacters = allowedCharacters;
    }

    @Override
    public boolean isDelimiter(char character) {
        return ' ' == character;
    }

    @Override
    public boolean isDomainCharacter(char character) {
        return acceptsAllCharacters || allowedCharacters.contains(character);
    }

    @Override
    public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
