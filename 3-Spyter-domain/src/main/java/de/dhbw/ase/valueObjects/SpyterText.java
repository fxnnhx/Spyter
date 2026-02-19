package de.dhbw.ase.valueObjects;

import java.util.ArrayList;
import java.util.List;

public class SpyterText {
    private final List<SpyterCharacter> characters;

    public SpyterText(List<SpyterCharacter> characters) {
        this.characters = characters;
    }

    public SpyterText(String text) {
        List<SpyterCharacter> spyterCharacters = new ArrayList<>();
        for (char c : text.toCharArray()) {
            SpyterCharacter.tryFrom(c).ifPresent(spyterCharacters::add);
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
}

// [abc a  ]