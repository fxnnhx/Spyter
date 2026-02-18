package de.dhbw.ase.valueObjects;

public class TypedCharacter {
    private final SpyterCharacter value;
    private final CharacterCorrectionType correctionType;

    public TypedCharacter(SpyterCharacter character, CharacterCorrectionType correctionType) {
       this.value = character;
       this.correctionType = correctionType;
    }
}