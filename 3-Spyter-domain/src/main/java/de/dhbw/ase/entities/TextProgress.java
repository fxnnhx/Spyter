package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;

import java.util.ArrayList;
import java.util.List;

public class TextProgress {
    private final SpyterText text;
    private final List<CharacterCorrectionType> characterCorrections;
    private int progressIndex = 0;

    public TextProgress(SpyterText text) {
        this.text = text;
        this.characterCorrections = new ArrayList<>(text.length());
    }

    public void advance(CharacterCorrectionType correctionType) {
        assert !isFinished();
        this.characterCorrections.add(correctionType);
        progressIndex++;
    }

    public SpyterCharacter getNextCharacter() {
        assert !isFinished();
        return this.text.characterAt(progressIndex);
    }

    public boolean isNextChar(SpyterCharacter character) {
        assert !isFinished();
        return this.text.characterAt(progressIndex).equals(character);
    }

    public void removeLastChar() {
        if (progressIndex > 0) {
            this.characterCorrections.removeLast();
            progressIndex--;
        }
    }

    public SpyterText getText() {
        return this.text;
    }

    public boolean isFinished() {
        return this.progressIndex >= text.length();
    }

    public TypedText getTypedText() {
        return new TypedText(this.text, this.characterCorrections);
    }
}
