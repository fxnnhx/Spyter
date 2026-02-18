package de.dhbw.ase.valueObjects;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpyterTextTest {


    @Test
    void getWords() {
        String testString = "ab cd";
        List<SpyterWord> expectedWords = List.of(
                new SpyterWord(List.of(new SpyterCharacter('a'), new SpyterCharacter('b'), new SpyterCharacter(' '))),
                new SpyterWord(List.of(new SpyterCharacter('c'), new SpyterCharacter('d')))
        );
        SpyterText text = new SpyterText(testString);

        text.getWords().equals(expectedWords);
    }
}