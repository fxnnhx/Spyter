package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.TypedCharacter;

import java.util.ArrayList;
import java.util.List;

public class TypedText {
    private final List<TypedCharacter> typedCharacters;

    public TypedText(int size) {
        this.typedCharacters = new ArrayList<>(size);
    }

    public void push(TypedCharacter typedCharacter) {
        this.typedCharacters.add(typedCharacter);
    }

    public void pop() {
        if (!this.typedCharacters.isEmpty()) {
            this.typedCharacters.removeLast();
        }
    }

    public int length() {
        return this.typedCharacters.size();
    }
}
