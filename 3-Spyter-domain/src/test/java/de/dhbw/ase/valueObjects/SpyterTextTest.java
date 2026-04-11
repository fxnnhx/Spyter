package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.helpers.FakeCharacterDomain;
import org.junit.jupiter.api.Test;

import java.util.List;

class SpyterTextTest {


    @Test
    void getWords() {
        SpyterText text = new SpyterText(new FakeCharacterDomain(List.of('a','b','c','d',' ')), "ab cd");
        List<SpyterCharacter> characters = text.getCharacters();
        List<SpyterWord> expectedWords = List.of(
                new SpyterWord(List.of(characters.get(0), characters.get(1), characters.get(2))),
                new SpyterWord(List.of(characters.get(3), characters.get(4)))
        );
        assert(text.getWords().equals(expectedWords));
    }

    @Test
    void createFromString() {
        SpyterText text = new SpyterText(new FakeCharacterDomain(List.of('a','b','c','d',' ')), "ab");
        List<SpyterCharacter> expectedCharacters = List.of(
                SpyterCharacter.tryFrom(new FakeCharacterDomain(List.of('a','b')),'a').get(),
                SpyterCharacter.tryFrom(new FakeCharacterDomain(List.of('a','b')),'b').get()
        );
        assert(text.getCharacters().equals(expectedCharacters));
    }

    @Test
    void getCharacters() {
        SpyterText text = new SpyterText(new FakeCharacterDomain(List.of('a','b',' ')), "ab");
        assert text.getCharacters().size() == 2;
        assert text.getCharacters().get(0).getValue() == 'a';
        assert text.getCharacters().get(1).getValue() == 'b';
    }

}
