package de.dhbw.ase;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.SpyterWord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

class RandomTextGeneratorTest {

    @Test
    void generate() {
        int minWordLength = 3;
        int maxWordLength = 5;
        int wordCount = 10;
        SpyterText textBase = new SpyterText(new FakeCharacterDomain(), "a ");
        SpyterText generatedText = new RandomTextGenerator(minWordLength, maxWordLength, wordCount).generate(textBase);
        Assertions.assertEquals(wordCount, generatedText.getWords().size());

        OptionalInt actualMinWordLength = generatedText.getWords().stream().mapToInt(SpyterWord::getCharacterCount).min();
        Assertions.assertTrue(actualMinWordLength.isPresent());
        Assertions.assertTrue(minWordLength <= actualMinWordLength.getAsInt());

        OptionalInt actualMaxWordLength = generatedText.getWords().stream().mapToInt(SpyterWord::getCharacterCount).max();
        Assertions.assertTrue(actualMaxWordLength.isPresent());
        Assertions.assertTrue(maxWordLength +1 >= actualMaxWordLength.getAsInt());
    }

    class FakeCharacterDomain implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            return character == ' ';
        }

        @Override
        public boolean isDomainCharacter(char character) {
            return true;
        }

        @Override
        public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            throw new UnsupportedOperationException("Not supported");
        }
    }

}