package de.dhbw.ase.constants;

import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public interface CharacterDomain {
    boolean isDelimiter(char character);
    boolean isDomainCharacter(char character);
    KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character);
}
