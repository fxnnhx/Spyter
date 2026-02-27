package de.dhbw.ase.constants;

import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public interface CharacterDomain {
    boolean isDelimiter(char character);

    static boolean isDomainCharacter(char character) {
        return CharacterDomain.characterToKeyStrokeCount.containsKey(character);
    }

    static KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
        return CharacterDomain.characterToKeyStrokeCount.get(character.getValue());
    }
}
