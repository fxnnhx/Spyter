package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.SpyterWord;

import java.util.List;

public record TypedText(SpyterText text, List<CharacterCorrectionType> characterCorrections) {

    public long getCorrectCharacterCount() {
        return this.characterCorrections.stream().filter((correctionType -> correctionType == CharacterCorrectionType.CORRECT)).count();
    }

    public long getWordCount() {
        return this.text.getWords().size();
    }
}
