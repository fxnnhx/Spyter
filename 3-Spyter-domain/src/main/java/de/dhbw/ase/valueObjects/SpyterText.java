package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;

import java.util.ArrayList;
import java.util.List;

public class SpyterText {
    private final List<SpyterCharacter> characters;

    public SpyterText(List<SpyterCharacter> characters) {
        this.characters = characters;
    }

    public SpyterText(CharacterDomain domain, String text) {
        List<SpyterCharacter> spyterCharacters = new ArrayList<>();
        for (char c : text.toCharArray()) {
            SpyterCharacter.tryFrom(domain, c).ifPresent(spyterCharacters::add);
        }
        this.characters = spyterCharacters;
    }

    public List<SpyterWord> getWords() {
        List<SpyterWord> words = new ArrayList<>();
        int beginOfWord = 0, endOfWord = 0;

        for (SpyterCharacter character : this.characters) {
            endOfWord++;
            if (character.isDelimiter()) {
                words.add(new SpyterWord(characters.subList(beginOfWord, endOfWord)));
                beginOfWord = endOfWord;
            }
        }

        if (beginOfWord < endOfWord){
            words.add(new SpyterWord(characters.subList(beginOfWord, endOfWord)));
        }

        return words;
    }

    public List<SpyterCharacter> getCharacters() {
        return characters;
    }
}
