package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;

public class TextProgress {
    private final SpyterText text;
    private final TypedText typedText;

    public TextProgress(SpyterText text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void advance(CharacterCorrectionType correctionType) {
       throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isNextChar(SpyterCharacter character){
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void remoteLastCharacter() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isFinished() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
