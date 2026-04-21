package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

import java.util.*;

public class RandomTextGenerator implements TextGenerator {
    final int minWordLength;
    final int maxWordLength;
    final int wordAmount;

    public RandomTextGenerator(int minWordLength, int maxWordLength, int wordAmount) {
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
        this.wordAmount = wordAmount;
    }

    private List<SpyterCharacter> getDelimiter(SpyterText textBase) {
        Set<SpyterCharacter> delimiters = new HashSet<>();
        textBase.getCharacters().stream().filter(SpyterCharacter::isDelimiter).forEach(delimiters::add);
        if (delimiters.isEmpty()) {
            return textBase.getCharacters();
        }
        return List.copyOf(delimiters);
    }

    public RandomTextGenerator() {
        this(3,10,100);
    }

    @Override
    public SpyterText generate(SpyterText textBase) {
        List<SpyterCharacter> textBaseCharacters = textBase.getCharacters();
        List<SpyterCharacter> delimiters = getDelimiter(textBase);
        List<SpyterCharacter> characters = new LinkedList<>();

        for (int i = 0; i < wordAmount; i++) {
            characters.addAll(generateWord(textBaseCharacters));
            characters.add(this.generateDelimiter(delimiters));
        }
        characters.removeLast();
        return new SpyterText(characters);
    }

    private List<SpyterCharacter> generateWord(List<SpyterCharacter> textBaseCharacters) {
        List<SpyterCharacter> word = new ArrayList<>();
        Random random = new Random();
        int currentWordLength = random.nextInt(minWordLength, maxWordLength+1);
        for (int j = 0; j < currentWordLength; j++) {
            word.add(this.generateCharacter(textBaseCharacters));
        }
        return word;
    }

    private SpyterCharacter generateDelimiter(List<SpyterCharacter> delimiters) {
        List<SpyterCharacter> shuffleList = new ArrayList<>();
        shuffleList.addAll(delimiters);
        Collections.shuffle(shuffleList);
        return shuffleList.getFirst();
    }

    private SpyterCharacter generateCharacter(List<SpyterCharacter> baseCharacters) {
        SpyterCharacter character;
        do {
            List<SpyterCharacter> shuffleList = new ArrayList<>();
            shuffleList.addAll(baseCharacters);
            Collections.shuffle(shuffleList);
            character = shuffleList.getFirst();
        } while (character.isDelimiter());
        return character;
    }
}
