package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

import java.util.*;

public class RandomTextGenerator implements TextGenerator {
    List<SpyterCharacter> textBase;
    int minWordLength = 3;
    int maxWordLength = 10;
    int wordAmount = 100;
    List<SpyterCharacter> delimiters;

    public RandomTextGenerator(SpyterText textBase, int minWordLength, int maxWordLength, int wordAmount) {
    this.textBase = textBase.getCharacters();
    this.minWordLength = minWordLength;
    this.maxWordLength = maxWordLength;
    this.wordAmount = wordAmount;
    this.delimiters = getDelimiter(textBase);
    }

    private List<SpyterCharacter> getDelimiter(SpyterText textBase) {
        Set<SpyterCharacter> delimiters = new HashSet<>();
        textBase.getCharacters().stream().filter(SpyterCharacter::isDelimiter).forEach(delimiters::add);
        return List.copyOf(delimiters);
    }

    public RandomTextGenerator(SpyterText textBase) {
    this.textBase = textBase.getCharacters();
    this.delimiters = getDelimiter(textBase);
    }

    @Override
    public SpyterText generate(SpyterText text) {
        List<SpyterCharacter> characters = new LinkedList<>();

        for (int i = 0; i < wordAmount; i++) {
            Random random = new Random();
            int currentWordLength = random.nextInt(minWordLength, maxWordLength+1);
            for (int j = 0; j < currentWordLength; j++) {
                characters.add(this.generateCharacter());
            }
            characters.add(this.generateDelimiter());
        }
        return new SpyterText(characters);
    }

    private SpyterCharacter generateDelimiter() {
        Collections.shuffle(delimiters);
        return delimiters.getFirst();
    }

    private SpyterCharacter generateCharacter() {
        SpyterCharacter character;
        do {
            Collections.shuffle(textBase);
            character = textBase.getFirst();
        } while (character.isDelimiter());
        return character;
    }
}
