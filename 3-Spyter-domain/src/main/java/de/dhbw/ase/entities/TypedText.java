package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.SpyterWord;

import java.util.List;

public record TypedText(SpyterText text, List<CharacterCorrectionType> characterCorrections) {

    public long getCorrectCharacterCount() {
        return this.characterCorrections.stream().filter((correctionType -> correctionType == CharacterCorrectionType.CORRECT)).count();
    }

    public long getCorrectWordCount() {
        int correctWordCount = 0;
        int characterIndex = 0;
        for (SpyterWord word : this.text.getWords()) {
            int wordLength = word.getCharacterCount();
            boolean wordIsCorrect = this.characterCorrections.subList(characterIndex, characterIndex + wordLength).stream().allMatch(correctionType -> correctionType == CharacterCorrectionType.CORRECT);
            if (wordIsCorrect) {
                correctWordCount++;
            }
            characterIndex += wordLength;
        }
        return correctWordCount;
    }
}
