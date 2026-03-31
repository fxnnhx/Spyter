package de.dhbw.ase;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTextGeneratorTest {

    @Test
    void generate() {
        SpyterText baseText = new SpyterText(new FakeCharacterDomain(), "abc");
        Assertions.assertEquals(baseText, new SimpleTextGenerator().generate(baseText));
    }

    class FakeCharacterDomain implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            throw new UnsupportedOperationException("Not supported");
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