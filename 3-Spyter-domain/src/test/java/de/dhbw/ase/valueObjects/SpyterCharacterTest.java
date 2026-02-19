package de.dhbw.ase.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpyterCharacterTest {

    @Test
    void getValue() {
        char c = 'a';
        SpyterCharacter spyterCharacter = SpyterCharacter.tryFrom(c).get();
        assertEquals(c, spyterCharacter.getValue());
    }
}