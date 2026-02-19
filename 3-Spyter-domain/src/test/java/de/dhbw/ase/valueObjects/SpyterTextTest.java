package de.dhbw.ase.valueObjects;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpyterTextTest {


    @Test
    void getWords() {
        SpyterText text = new SpyterText("ab cd");
        List<SpyterCharacter> characters = text.getCharacters();
        List<SpyterWord> expectedWords = List.of(
                new SpyterWord(List.of(characters.get(0), characters.get(1), characters.get(2))),
                new SpyterWord(List.of(characters.get(3), characters.get(4)))
        );
        assertTrue(text.getWords().equals(expectedWords));
    }
}