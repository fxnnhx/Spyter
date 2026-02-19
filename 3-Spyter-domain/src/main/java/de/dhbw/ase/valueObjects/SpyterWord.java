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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SpyterWord)) return false;
        SpyterWord that = (SpyterWord) object;
        return java.util.Objects.equals(characters, that.characters);
    }

    public int hashCode() {
        return Objects.hash(characters);
    }
}
