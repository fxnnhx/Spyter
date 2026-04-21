package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.helpers.FakeCharacterDomain;
import org.junit.jupiter.api.Test;

import java.util.List;

class SpyterCharacterTest {

    @Test
    void getValue() {
        char c = 'a';
        SpyterCharacter spyterCharacter = SpyterCharacter.tryFrom(new FakeCharacterDomain(List.of(c)), c).get();
        assert(c == spyterCharacter.getValue());
    }
}
