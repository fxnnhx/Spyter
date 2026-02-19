package de.dhbw.ase.valueObjects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpyterTextTest {


    @Test
    void getWords() {
        List<SpyterCharacter> testString = toListOfSpyterCharacters("ab cd");
        List<SpyterWord> expectedWords = List.of(
                new SpyterWord(List.of(testString.get(0), testString.get(1), testString.get(2))),
                new SpyterWord(List.of(testString.get(3), testString.get(4)))
        );
        SpyterText text = new SpyterText(testString);
        assertTrue(text.getWords().equals(expectedWords));
    }

    private static List<SpyterCharacter> toListOfSpyterCharacters(String input) {
        List<SpyterCharacter> spyterCharacters = new ArrayList<>();
        for (char c : input.toCharArray()) {spyterCharacters.add(SpyterCharacter.tryFrom(c).get());}
        return spyterCharacters;
    }
}