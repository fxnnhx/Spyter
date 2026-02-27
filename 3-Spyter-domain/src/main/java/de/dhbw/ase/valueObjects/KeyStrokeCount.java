package de.dhbw.ase.valueObjects;

import java.util.Objects;
import java.util.Optional;

public class KeyStrokeCount {
    private final int value;
    public KeyStrokeCount(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("Key stroke count should be >= 1");
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof KeyStrokeCount that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
