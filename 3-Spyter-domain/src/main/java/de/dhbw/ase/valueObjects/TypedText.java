package de.dhbw.ase.valueObjects;

import java.util.List;

public class TypedText {
    private final List<TypedCharacter> typedCharacters;

    public TypedText(List<TypedCharacter> typedCharacters) {
        this.typedCharacters = typedCharacters;
    }
}
