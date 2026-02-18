package de.dhbw.ase.valueObjects;

import java.util.List;

public class SpyterWord {
    private final List<SpyterCharacter> characters;
    public SpyterWord(List<SpyterCharacter> characters) {
        this.characters = characters;
    }
    public int getCharacterCount() {
        return this.characters.size();
    }
}
