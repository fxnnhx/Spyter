package de.dhbw.ase.valueObjects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpyterTextTest {


    @Test
    void getWords() {
        List<SpyterCharacter> testString = List.of(
                SpyterCharacter.tryFrom('a').get(),
                SpyterCharacter.tryFrom('b').get(),
                SpyterCharacter.tryFrom(' ').get(),
                SpyterCharacter.tryFrom('c').get(),
                SpyterCharacter.tryFrom('d').get()
        );
        List<SpyterWord> expectedWords = List.of(
                new SpyterWord(List.of(testString.get(0), testString.get(1), testString.get(2))),
                new SpyterWord(List.of(testString.get(3), testString.get(4)))
        );
        SpyterText text = new SpyterText(testString);
        assertTrue(text.getWords().equals(expectedWords));
    }
}