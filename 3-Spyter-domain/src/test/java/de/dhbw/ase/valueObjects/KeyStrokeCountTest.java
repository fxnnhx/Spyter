package de.dhbw.ase.valueObjects;

import org.junit.jupiter.api.Test;

public class KeyStrokeCountTest {
    @Test
    void throwsOnNegativeValue() {
        try {
            new KeyStrokeCount(-1);
            assert false;
        } catch (IllegalArgumentException _) {
        }
    }

    @Test
    void throwsOnZeroValue () {
        try {
            new KeyStrokeCount(0);
            assert false;
        } catch (IllegalArgumentException _) {
        }
    }

    @Test
    void passesOnPositiveValue() {
        try {
            new KeyStrokeCount(1);
        } catch (IllegalArgumentException _) {
            assert false;
        }
    }
}
