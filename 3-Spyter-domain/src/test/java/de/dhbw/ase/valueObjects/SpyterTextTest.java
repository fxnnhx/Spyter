package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;
import org.junit.jupiter.api.Test;

import java.util.List;

class SpyterTextTest {


    @Test
    void getWords() {
        SpyterText text = new SpyterText(new MockCharacterDomain(), "ab cd");
        List<SpyterCharacter> characters = text.getCharacters();
        List<SpyterWord> expectedWords = List.of(
                new SpyterWord(List.of(characters.get(0), characters.get(1), characters.get(2))),
                new SpyterWord(List.of(characters.get(3), characters.get(4)))
        );
        assert(text.getWords().equals(expectedWords));
    }

    @Test
    void createFromString() {
        SpyterText text = new SpyterText(new MockCharacterDomain(), "ab");
        List<SpyterCharacter> expectedCharacters = List.of(
                SpyterCharacter.tryFrom(new MockCharacterDomain(),'a').get(),
                SpyterCharacter.tryFrom(new MockCharacterDomain(),'b').get()
        );
        assert(text.getCharacters().equals(expectedCharacters));
    }

    class MockCharacterDomain implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            return character == ' ';
        }

        @Override
        public boolean isDomainCharacter(char character) {
            return List.of('a','b','c','d',' ').contains(character);
        }

        @Override
        public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
